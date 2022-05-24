package de.bitgrip.codingchallenge.infrastructure;

import de.bitgrip.codingchallenge.entity.District;
import de.bitgrip.codingchallenge.entity.Vote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ImportCSV {
        private static final Logger logger = LoggerFactory.getLogger(ImportCSV.class);

        private String districtsFilePath = "btw21_strukturdaten.csv";
        private String votesFilePath = "btw21_ergebnisse.csv";

        private List<District> getSaltDistricts() {

                List<District> districts = new ArrayList<>();
                FileResourcesUtils app = new FileResourcesUtils();
                InputStream is = app.getFileFromResourceAsStream(districtsFilePath);
                List<List<String>> districtData = app.getLines(is);
                int lineCursor = 0;
                for (List<String> column : districtData) {

                        if (lineCursor != 0) {
                                // logger.info("\n" + column.toString());
                                // logger.info("\n0:Gebietsnummer:id : " + column.get(0));
                                // logger.info("\n1:Gebietsname:name : " + column.get(1));
                                // logger.info("\n2:Fläche:area : " + column.get(2));
                                // logger.info("\n3:Bevölkerung - insgesamt (in 1000):population : " +
                                // column.get(3));
                                // logger.info("\n4:Bevölkerung Ausländer/-innen (%):foreignerPct : " +
                                // column.get(4));
                                // logger.info("\n7:Alter unter 18 (%):ageLt18Pct : " + column.get(7));
                                // logger.info("\n12:Alter 75 und mehr (%):ageGt75Pct : " + column.get(12));
                                // logger.info("\n13:Bodenfläche - Siedlung und Verkehr (%):areaSettlePct : "
                                // + column.get(13));
                                // logger.info("\n14:Bodenfläche - Vegetation und Gewässer (%):areaNaturePct : "
                                // + column.get(14));
                                // logger.info("\n16:Wohnfläche (je EW):livingSpace : " + column.get(16));
                                // logger.info("\n17:PKW Insgesamt (je 1000 EW):cars : " + column.get(17));
                                // logger.info("\n18:Verfügbares Einkommen (EUR je EW):income: " +
                                // column.get(18));
                                // logger.info("\n19:Bruttoinlandsprodukt (EUR je EW):bip : " + column.get(19));
                                // logger.info("\n20:Arbeitslosenquote - insgesamt:unemploymentRate : " +
                                // column.get(20));

                                districts.add(new District(
                                                Integer.parseInt(column.get(0)), // 0:Gebietsnummer:id
                                                column.get(1), // 1:Gebietsname:name
                                                column.get(2), // 2:Fläche:area
                                                column.get(3), // 3:Bevölkerung - insgesamt (in 1000):population
                                                column.get(4), // 4:Bevölkerung Ausländer/-innen (%):foreignerPct
                                                column.get(7), // 7:Alter unter 18 (%):ageLt18Pct
                                                column.get(12), // 12:Alter 75 und mehr (%):ageGt75Pct
                                                column.get(13), // 13:Bodenfläche - Siedlung und Verkehr
                                                                // (%):areaSettlePct
                                                column.get(14), // 14:Bodenfläche - Vegetation und Gewässer
                                                                // (%):areaNaturePct
                                                column.get(16), // 16:Wohnfläche (je EW):livingSpace
                                                column.get(17), // 17:PKW Insgesamt (je 1000 EW):cars
                                                column.get(18), // 18:Verfügbares Einkommen (EUR je EW):income
                                                column.get(19), // 19:Bruttoinlandsprodukt (EUR je EW):bip
                                                column.get(20))); // 20:Arbeitslosenquote - insgesamt:unemploymentRate

                        }
                        lineCursor++;
                }
                return districts;
        }

        private List<Vote> getSaltVode(List<District> districtData) {

                List<Vote> votes = new ArrayList<>();
                FileResourcesUtils app = new FileResourcesUtils();
                InputStream is = app.getFileFromResourceAsStream(votesFilePath);
                List<List<String>> voteData = app.getLines(is);
                int lineCursor = 0;
                for (List<String> column : voteData) {

                        if (lineCursor != 0) {
                                int districtId = Integer.parseInt(column.get(0));
                                District district = districtData.stream().filter(x -> x.getId() == districtId)
                                                .findFirst().orElse(new District());
                                // logger.info("\ncolumn : " + column.toString());
                                // logger.info("\ncolumn size : " + column.size());
                                if (district.getId() != null && column.size() == 7) {
                                        votes.add(new Vote(
                                                        column.get(1),
                                                        column.get(2),
                                                        column.get(3),
                                                        Short.parseShort(column.get(4)),
                                                        column.get(5),
                                                        column.get(6),
                                                        district));
                                }

                        }
                        lineCursor++;
                }
                return votes;
        }

        public List<District> getDistrict() {

                List<District> districtData = getSaltDistricts();
                List<Vote> voteData = getSaltVode(districtData);
                List<District> districts = new ArrayList<>();
                for (District district : districtData) {
                        Set<Vote> votes = voteData.stream().filter(x -> x.getDistrict().getId() == district.getId())
                                        .collect(Collectors.toSet());
                        district.setVotes(votes);
                        districts.add(district);
                }
                return districts;
        }

        public List<District> getDistrictTest() {
                List<District> districts = new ArrayList<>();

                final District districtA = new District(
                                1,
                                "District 1",
                                "1",
                                "2",
                                "3",
                                "4",
                                "5",
                                "6",
                                "7",
                                "8",
                                "9",
                                "10",
                                "11",
                                "12");

                Set<Vote> voteAs = new HashSet<>();
                voteAs.add(new Vote(
                                "Partei",
                                "CDU",
                                "1",
                                Short.parseShort("2"),
                                "3",
                                "4",
                                districtA));

                voteAs.add(new Vote(
                                "Partei",
                                "SPD",
                                "1",
                                Short.parseShort("2"),
                                "3",
                                "4",
                                districtA));

                districtA.setVotes(voteAs);

                final District districtB = new District(
                                2,
                                "District 2",
                                "1",
                                "2",
                                "3",
                                "4",
                                "5",
                                "6",
                                "7",
                                "8",
                                "9",
                                "10",
                                "11",
                                "12");

                Set<Vote> voteBs = new HashSet<>();
                voteBs.add(new Vote(
                                "Partei",
                                "CDU",
                                "1",
                                Short.parseShort("2"),
                                "3",
                                "4",
                                districtB));

                voteBs.add(new Vote(
                                "Partei",
                                "SPD",
                                "1",
                                Short.parseShort("2"),
                                "3",
                                "4",
                                districtB));

                voteBs.add(new Vote(
                                "Partei",
                                "FDP",
                                "1",
                                Short.parseShort("2"),
                                "3",
                                "4",
                                districtB));

                districtB.setVotes(voteBs);

                districts.add(districtB);

                districts.add(districtA);

                return districts;
        }
}
