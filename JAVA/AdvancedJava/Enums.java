
public class Enums {
    // when a enum runs a object is created for each constants 
    // passing through the constructor and each object has its label set
    // When we call again as Element.NE it returns already created labels value
    enum Element {
        H("Hydrogen"),
        HE("Helium"),
        // ...
        NE("Neon");
        public final String label;

        private Element(String label) {
            this.label = label;
        }
        
    }
    public static void main(String[] args) {
        System.out.println(Element.HE.label);
        System.out.println(Element.NE.label);
    }


}

