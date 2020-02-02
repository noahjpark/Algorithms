public class URLify {

    // Time: O(n)
    // Space: O(1)
    public static char[] url(char[] st, int l){
        for(int i = l - 1; i >= 0; i--){
            if(st[i] == ' '){
                int j = l - 1;
                l += 2;
                int trailer = l - 1;
                while(j != i){
                    st[trailer--] = st[j--];
                }
                st[i] = '%';
                st[i + 1] = '2';
                st[i-- + 2] = '0';
            }
        }
        return st;
    }

    public static void main(String[] args){
        String str = "Mr John Smith    ";
        char[] st = str.toCharArray();
        int l = 0;
        for(int i = 0; i < st.length; i++){
            if(st[i] == ' ' && st[i+1] == ' '){
                l = i;
                break;
            }
        }
        char[] c = url(st, l);
        for(char ch : c){
            System.out.print(ch);
        }
    }
}
