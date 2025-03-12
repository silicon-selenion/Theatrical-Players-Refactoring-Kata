package theatricalplays;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        StringBuilder result = new StringBuilder(String.format("Statement for %s\n", invoice.customer));
        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (var perf : invoice.performances) {
            var play = plays.get(perf.playID);
            var playType = getPlayType(play.type);
            var performanceAmount = playType.getPrice(perf.audience);
            var performanceVolumeCredits = playType.getVolumeCredits(perf.audience);

            result.append(String.format("  %s: %s (%s seats)\n", play.name, frmt.format(performanceAmount / 100), perf.audience));
            totalAmount += performanceAmount;
            volumeCredits += performanceVolumeCredits;
        }
        result = new StringBuilder(addFooter(result.toString(), frmt, totalAmount, volumeCredits));
        return result.toString();
    }

    PlayType getPlayType(String playType) {
        try {
            return PlayType.valueOf(playType.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new Error("unknown type: ${play.type}");
        }
    }

    private static String addFooter(String result, NumberFormat frmt, int totalAmount, int volumeCredits) {
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }


}
