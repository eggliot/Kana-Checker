public class Main {
    /**
     * Run main to use test cases, otherwise use KanaCheck.main and provide it input from the terminal
     * @param args test cases array
     */
    public static void main(String[] args) {
        KanaCheck kanaCheck = new KanaCheck();

        // Test cases
        String CaseA = "So,N,Ta,I,No,Ra,wo,To,Ra,Ya,U,Ho,Ru,Sa,Tsu,Ha,そ,ん,た,い,の,ら,を,と,ら,や,う,ほ,る,さ,つ,は,"; // ending comma
        String CaseB = ",So,N,Ta,I,No,Ra,wo,To,Ra,Ya,U,Ho,Ru,Sa,Tsu,Ha,そ,ん,た,い,の,ら,を,と,ら,や,う,ほ,る,さ,つ,は"; // beginning comma
        String CaseC = "So,N,Ta,I,No,Ra,wo,To,Ra,Ya,U,Ho,Ru,Sa,Tsu,Ha,そ,ん,た,い,の,ら,を,と,ら,や,う,ほ,る,さ,つ,は"; // ideal case
        String CaseAinv = "そ,ん,た,い,の,ら,を,と,ら,や,う,ほ,る,さ,つ,は,So,N,Ta,I,No,Ra,wo,To,Ra,Ya,U,Ho,Ru,Sa,Tsu,Ha,"; // ending comma inv
        String CaseBinv = ",そ,ん,た,い,の,ら,を,と,ら,や,う,ほ,る,さ,つ,は,So,N,Ta,I,No,Ra,wo,To,Ra,Ya,U,Ho,Ru,Sa,Tsu,Ha"; // beginning comma inv
        String CaseCinv = "そ,ん,た,い,の,ら,を,と,ら,や,う,ほ,る,さ,つ,は,So,N,Ta,I,No,Ra,wo,To,Ra,Ya,U,Ho,Ru,Sa,Tsu,Ha"; // ideal case inv

        String[] caseArray = {CaseA, CaseB, CaseC, CaseAinv, CaseBinv, CaseCinv};

        KanaCheck.main(caseArray);
    }
}
