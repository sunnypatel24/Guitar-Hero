// A class to create vibrating guitar strings in order to simulate a guitar.
public class GuitarString {
    private static final int samplingRate = 44100;
    private static final double randomValueScale = 0.5;
    private static final double decayFactor = 0.994;
    private static final double average = 0.5;

    private double frequency;
    private int numTics;
    private int desiredCapacity;
    private RingBuffer ringBuffer;

    /* Constructor that creates a guitar string of the given frequency, using a sampling rate of 
     * 44,100.
     * @param frequency Sound frequency used to get the desired capacity for the ringbuffer.
    */
    public GuitarString(double frequency) {
        this.frequency = frequency;
        this.numTics = 0;
        this.desiredCapacity = (int) Math.round((samplingRate / frequency));
        this.ringBuffer = new RingBuffer(desiredCapacity);
        for (int bufferIndex = 0; bufferIndex < desiredCapacity; bufferIndex++) {
            ringBuffer.enqueue(0);
        }
    }


    /* Creates a guitar string whose size and initial values are given by the array.
     * @param init Array used to initialize the size of the ringbuffer and the values within it.
    */
    public GuitarString(double[] init) {
        ringBuffer = new RingBuffer(init.length);
        for (int index = 0; index < init.length; index++) {
            ringBuffer.enqueue(init[index]);
        }
    }


    // Sets the buffer to white noise.
    public void pluck() {
        for (int bufferIndex = 0; bufferIndex < desiredCapacity; bufferIndex++) {
            double replaceNum = Math.random() - randomValueScale;
            ringBuffer.dequeue();
            ringBuffer.enqueue(replaceNum);
        }
    }


    // Advances the simulation one time step.
    public void tic() {
        numTics++;
        double frontSample = ringBuffer.dequeue();
        double newFrontValue = ringBuffer.peek();
        double valueAtEnd = decayFactor * average  * (frontSample + newFrontValue);
        ringBuffer.enqueue(valueAtEnd);
    }

    /* Returns the current sample.
     * @return Returns the value at the front of the buffer.
    */
    public double sample() {
        return ringBuffer.peek();
    }


    /* Returns the total number of times tic() was called.
     * @return numTics Holds the number of times the tic() method was called.
    */
    public int time() {
        return numTics;
    }

}