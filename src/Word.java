public class Word {
    private String countries = null;

    //Constructor
    Word(String countries) {
        this.countries = countries;
    }

    /**
     * match() checks to see if the users guess matches any letters in the random country
     * @param letter : char,
     * @param country : String,
     * @param answer : char[],
     * @return char[]
     */
    public static char[] match(char letter,String country,char[] answer) {
        for (int i = 0; i < answer.length; i++) {
            if (letter == country.charAt(i)) {
                answer[i] = letter;
            }
        }
        System.out.println(answer);
        return answer;
    }
}