package ra.linearandconstant.graph

import spock.lang.Specification

/**
 * @author Remus Amalinei
 */
class ColorMapTest extends Specification {

    // TODO add tests for invalid input

    void 'should color countries'() {
        given:
        Map<String, List<String>> countryMap = [
            WA: ['ID', 'OR'],
            OR: ['WA', 'ID', 'NV', 'CA'],
            CA: ['OR', 'NV', 'AZ'],
            ID: ['MT', 'WY', 'UT', 'NV', 'OR', 'WA'],
            NV: ['ID', 'UT', 'AZ', 'CA', 'OR'],
            MT: ['WY', 'ID'],
            WY: ['MT', 'CO', 'UT', 'ID'],
            UT: ['WY', 'CO', 'AZ', 'NV', 'ID'],
            AZ: ['UT', 'NM', 'CA', 'NV'],
            CO: ['WY', 'NM', 'UT'],
            NM: ['CO', 'AZ']
        ]

        when:
        Map<String, ColorMap.Color> countryColorMap = new ColorMap().color(countryMap)

        then:
        countryColorMap.keySet() == countryMap.keySet()
        eachCountryHasDifferentColorFromNeighbours(countryMap, countryColorMap)
    }

    private boolean eachCountryHasDifferentColorFromNeighbours(Map<String, List<String>> countryMap,
                                                               Map<String, ColorMap.Color> colorMap) {

        for (country in colorMap.keySet()) {
            ColorMap.Color countryColor = colorMap[country]
            for (neighbouringCountry in countryMap[country]) {
                ColorMap.Color neighbouringColor = colorMap[country]

                if (countryColor != neighbouringColor) {
                    return false
                }
            }
        }

        true
    }
}
