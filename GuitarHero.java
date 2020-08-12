// A program to simulate an interactive guitar player.
public class GuitarHero {
    private static final String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static final int middleHertz = 440;
    private static final double keyFrequency = 1.05956;
    private static final int keyString = 24;
    private static final int numKeys = 37;
    private static final double TEXT_POS_X = .45; 
    private static final double TEXT_POS_Y = .55;
    public static void main(String[] args) {
        GuitarString[] guitarStrings = createGuitarStrings();
        StdDraw.text(TEXT_POS_X, TEXT_POS_Y, "Type a character to play!");
        play(guitarStrings);
    }
    
    /* Creates an array containing 37 guitar string objects that models a piano keyboard.
     * @return guitarStrings Array that holds 37 guitarstring objects used to model the various
     *  keys you can press to get sound.
    */
    public static GuitarString[] createGuitarStrings() {
        GuitarString[] guitarStrings = new GuitarString[numKeys];
        for (int keyIndex = 0; keyIndex < keyboard.length(); keyIndex++) {
            guitarStrings[keyIndex] = new GuitarString(middleHertz * Math.pow(keyFrequency, keyIndex - keyString));
        }
        return guitarStrings;
    }
    
    /* The main input loop. Checks if the user has typed a key, and, if so, processes it.
     * Plucks the corresponding string. Computes the superposition of the samples and sends
     * them to standard audio. Advances the simulation of each guitar string by one step.
     * @param guitarStrings Array that holds 37 guitar string objects.
    */
    private static void play(GuitarString[] guitarStrings) {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if (keyboard.indexOf(key) != -1) {
                    guitarStrings[keyboard.indexOf(key)].pluck();
                }
            }
            double sample = 0;
            for (int keyIndex = 0; keyIndex < guitarStrings.length; keyIndex++) {
                sample += guitarStrings[keyIndex].sample();
            }
            StdAudio.play(sample);
            for (int keyIndex = 0; keyIndex < guitarStrings.length; keyIndex++) {
                guitarStrings[keyIndex].tic();
            }
        }
    }

}