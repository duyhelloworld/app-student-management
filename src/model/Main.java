public class Main {
    public static void main(String[] args) {
        
        Product iphone = new Product();
        iphone.setId(1L);
        iphone.setName("Iphone 13");
        iphone.setPrice(17);

        Product samsung = new Product(2L, "Samsung Ultra", 23);
        System.out.println(samsung.getId());
        
    }
}
