package theatricalplays;

enum PlayType {
    TRAGEDY(40000, 30, 1000, 0, 0),
    COMEDY(30000, 20, 500, 300, 10000);

    private final int basePrice;
    private final int audienceWithoutExtra;
    private final int extraPerPerson;
    private final int perAudienceFee;
    private final int extraFixedFee;

    PlayType(int basePrice, int audienceWithoutExtra, int extraPerPerson, int perAudienceFee, int extraFixedFee) {
        this.basePrice = basePrice;
        this.audienceWithoutExtra = audienceWithoutExtra;
        this.extraPerPerson = extraPerPerson;
        this.perAudienceFee = perAudienceFee;
        this.extraFixedFee = extraFixedFee;
    }

    int getPrice(int  audience) {
        int pice = basePrice;
        if (audience > audienceWithoutExtra) {
            pice += (audience - audienceWithoutExtra) * extraPerPerson;
        }
        pice += perAudienceFee * audience;
        pice += extraFixedFee;
        return pice;
    }

    int getVolumeCredits(int audience) {
        int volumeCredit = 0;

        volumeCredit += Math.max(audience - 30, 0);

        if(this == COMEDY) {
            volumeCredit += Math.floor((double) audience / 5);
        }

        return volumeCredit;
    }
}
