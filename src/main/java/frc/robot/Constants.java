package frc.robot;

/**
 * This class contains constants. This class should not be used for any other purpose.
 * All constants should be declared globally (i.e., public static). Do not put anything
 * functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public final static class Lighting {
        public final static int LIGHTING_PORT = 5;

        public enum Colors {
            // The following enum values are commented out for future use:
            HOTPINK   ("Hot Pink", 0.57),
            DARKRED   ("Dark Red", 0.59),
            RED       ("Red", 0.61),
            REDORANGE ("Red Orange", 0.63),
            ORANGE    ("Orange", 0.65),
            GOLD      ("Gold", 0.67),
            YELLOW    ("Yellow", 0.69),
            LAWNGREEN ("Lawn Green", 0.71),
            LIME      ("Lime", 0.73),
            DARKGREEN ("Dark Green", 0.75),
            GREEN     ("Green", 0.77),
            BLUEGREEN ("Blue Green", 0.79),
            AQUA      ("Aqua", 0.81),
            SKYBLUE   ("Sky Blue", 0.83),
            DARKBLUE  ("Dark Blue", 0.85),
            BLUE      ("Blue", 0.87),
            BLUEVIOLET ("Blue Violet", 0.89),
            VIOLET    ("Violet", 0.91),
            WHITE     ("White", 0.93),
            RAINBOWGLITTER ("Rainbow - Glitter", -0.89),
            RAINBOWSINELON ("Rainbow - Sinelon", -0.79),
            RAINBOWBEATS ("Rainbow - Beats Per Minute", -0.69),
            RAINBOWTWINKLES ("Rainbow - Twinkles", -0.55),
            RAINBOWWAVES ("Rainbow - Color Waves", -0.45),
            REDCHASE  ("Light Chase - Red", -0.31),
            BLUECHASE ("Light Chase - Blue", -0.29),
            OFF       ("Off", 0.99);

            public final String colorName;
            public final double colorValue;

            Colors(String colorName, double colorValue) {
                this.colorName = colorName;
                this.colorValue = colorValue;
            }

            public String getColorName() {
                return this.colorName;
            }

            public double getColorValue() {
                return this.colorValue;
            }
        }

        public final static Colors STARTING_COLOR = Colors.OFF;
    }
}