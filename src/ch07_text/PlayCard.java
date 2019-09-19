package ch07_text;

/**
 * 扑克牌
 */
public class PlayCard{

    private int number; // 扑克牌的面值: [1,..,13]
    private int type; // 扑克牌的花色：[1,..,4]

    public PlayCard(int number, int type) {
        this.number = number;
        this.type = type;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString(){
        String temp = "";
        switch(number){
            case 14: temp = "A"; break;
            case 11: temp = "J"; break;
            case 12: temp = "Q"; break;
            case 13: temp = "K"; break;
            default: temp = "" + number;
        }
        String result = "面值为：" + temp;
        switch(type){
            case 4: temp = "黑桃"; break;
            case 3: temp = "红桃"; break;
            case 2: temp = "梅花"; break;
            case 1: temp = "方砖"; break;
//            default: temp = "" + type;
        }
        result += ("，花色为：" + temp);
        return result;
    }

    public boolean isSame(PlayCard other){
        if(number == other.getNumber() && type == other.getType()){
            return true;
        }
        return false;
    }
}
