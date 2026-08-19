class Solution {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {

        Arrays.sort(products);

        List<List<String>> result = new ArrayList<>();

        String prefix = "";

        for (char ch : searchWord.toCharArray()) {

            prefix += ch;

            List<String> suggestions = new ArrayList<>();

            for (String product : products) {

                if (product.startsWith(prefix)) {
                    suggestions.add(product);

                    if (suggestions.size() == 3) {
                        break;
                    }
                }
            }

            result.add(suggestions);
        }

        return result;
    }
}