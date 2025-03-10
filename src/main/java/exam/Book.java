package exam;

// Book 클래스
public class Book {
    // field(속성)
    private String title; // title 인스턴스 field
    private int price; // price 인스턴스 field

    // 기본 생성자
    public Book(){}

    // Book 생성자
    public Book(String title, int price)
    {
        this.title = title;
        this.price = price;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
