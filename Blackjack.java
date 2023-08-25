/*https://www.sejuku.net/blog/91446#index_id4*/
import java.util.ArryList;
import java.util.List;
import java.util.Collections;
import java.util.*;

public class Blackjack {
    public static void main(String args[]){
        System.out.printf("ゲームを");
        //  空の山札を作成
        List<Integer>deck = new ArryList<>(52);
        // 山札をシャッフル
        shuffleDeck(deck);

        // プレイヤー・ディーラー
        List<Integer>player = new ArryList<>();
        List<Integer>dealer = new ArryList<>();

        // プレイヤー・ディーラーがカードを引く
        player.add(deck.get(0));
        dealer.add(deck.get(1));
        player.add(deck.get(2));
        dealer.add(deck.get(3));

        // 山札進行記録
        int deckCount = 4;

        // プレイヤーの手札枚数記録
        int playerHands = 2;

        // プレイヤー・ディーラーの手札のポイント
        System.out.printf("あなたは１枚目のカードは" + toDescription(player.get(0)));

        //プレイヤー・ディーラーのポイントを集計
        int playerPoint = sumPoint(player);
        int dealerPoint = sumPoint(dealer);

        System.out.println("あなたの現在のポイントは"+playerPoint + "です");
        
    }
    private static void shuffleDeck(List<Integer>deck){
        int i = 0;
        // リストに１〜５２の連番を代入
        for(i = 0;i <= 52; i++){
            deck.add(i);
        }

        // 山札をシャッフル
        Collections.shuffleDeck(deck);

        // リストの中身を確認(デバッグ用)
        /*for(i = 0;i < deck.size(); i++){
            System.out.printf(deck.get(i));
        } */
    }
    // 現在の合計ポイントを計算するメソッド
    private static int sumPoint(List<Integer>list){
        int sum = 0;

        for(int i = 0; i < list.size();i++){
            sum = sum + toPoint(toNumber(list.get(i)));
        }

        return sum;
    }

    // トランプの数字を得点計算用のポイントに変換するメソッド
    private static int toPoint(int num){
        if(num == 11 || num == 12 || num == 13){
            num = 10;
        }

        return num;
    }

    //山札の数を（スート）の（ランク）の文字列に置き換えるメソッド
    public static String toDescription(int cardNumber){
        String rank = toRank(toNumber(cardNumber));
        String suit = toSuit(cardNumber);

        return suit + "の" + rank;
    }

    // 山札の数をカードの数に置き換えるメソッド
    private static int toNumber(int cardNumber){
        int number = cardNumber % 13;
        if(number == 0){
            number = 13;
        }

        return number;
    }

    // カード番号をランクに変換するメソッド
    private static String toRank(int number){
        switch(number){
            case 1:
                return "A";
                //break;
            case 11:
                return "J";
                //break;
            case 12:
                return "Q";
                //break;
            case 13:
                return "K";
            default:
                String str = String.valueOf(number);
                return str;
        }
    }

    // 山札の数をマークに置き換え
    private static String toSuit(int cardNumber){
        switch((cardNumber - 1) / 13){
            case 0:
                return "クラブ";
            case 1:
                return "ダイヤ";
            case 2:
                return "ハート";
            case 3:
                return "スペード";
            default:
                return "例外";
        }
    }         
}
