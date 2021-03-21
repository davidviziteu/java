package optional;

public class ListCommand extends generalCommand{
    static public void listThings(CatalogOptional o){
        if(o.items.size() == 0) {
            System.out.println("No items in catalog");
            return;
        }
        o.items.forEach(i -> System.out.println(i.toString()));
    }
}
