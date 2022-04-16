package ra.linearandconstant.graph

/**
 * Color a map (take the USA as an example for the rectangle-like borders) so that any 2 neighbouring countries do not
 * have the same color.
 *
 * Hint: use the Four color theorem http://en.wikipedia.org/wiki/Four_color_theorem
 *
 * @author Remus Amalinei
 */
class ColorMap {

    static enum Color {
        RED, GREEN, BLUE, WHITE
    }

    Map<String, Color> color(Map<String, List<String>> countryMap) {
        Map<String, Color> countryColorMap = [:]

        for (country in countryMap.keySet()) {
            Set<Color> availableColorSet = EnumSet.allOf(Color)
            for (neighbouringCountry in countryMap[country]) {
                availableColorSet.remove(countryColorMap[neighbouringCountry])
            }

            countryColorMap[country] = availableColorSet.first()
        }

        countryColorMap
    }
}
