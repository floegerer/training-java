
class TextBox {

    Integer i;
    int j;
    
    public static void main(String[] args) {
        TextBox t = new TextBox();
        t.go();
    }
    
    public void go() {
        // j = i;
        i = j;
        System.out.println(j);
        System.out.println(i);
    }
    
}