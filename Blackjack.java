/*https://www.sejuku.net/blog/91446#index_id4*/
// import java.util.ArryList;
// import java.util.List;
//import java.util.Collections;
import java.util.*;

public class Blackjack {
    public static void main(String args[]){
        
        System.out.printf("ゲームを開始します");
        //  空の山札を作成
        List<Integer> deck = new ArrayList<>(52);
        // 山札をシャッフル
        shuffleDeck(deck);

        // プレイヤー・ディーラー
        List<Integer> player = new ArrayList<>();
        List<Integer> dealer = new ArrayList<>();

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
        System.out.println("あなたは１枚目のカードは" + toDescription(player.get(0)));
        System.out.println("ディーラーの１枚目のカードは" + toDescription(dealer.get(0)));
        System.out.println("あなたの２枚目のカードは" + toDescription(player.get(1)));
        System.out.println("ディーラーの2枚目のカードは秘密です");

        //プレイヤー・ディーラーのポイントを集計
        int playerPoint = sumPoint(player);
        int dealerPoint = sumPoint(dealer);

        System.out.println("あなたの現在のポイントは"+playerPoint + "です");
        
        while(true){
            System.out.println("カードを引きますか？Yes:y or No:n");
            //キーボードの入力を受けて変数strに代入する
            Scanner scan = new Scanner(System.in);
            String str = scan.next();
            if("n".equals(str)){
                break;
            }else if("y".equals(str)){
                //手札の追加とバーストチェック
                player.add(deck.get(deckCount));

                deckCount++;

                playerHands++;

                System.out.println("あなたの" + playerHands + "枚目のカードは" + toDescription(player.get(playerHands - 1)));

                playerPoint = sumPoint(player);

                System.out.println("現在の合計は" + playerPoint);
                
                if(isBusted(playerPoint)){
                    System.out.println("残念、バーストしてしまいました");
                }
            }else{
                System.out.println("あなたの入力は" + str + "です");
            }
        }

        while(true){
            if(dealerPoint >= 17){
                break;
            }else{
                // 手札に山札から1枚加える
                dealer.add(deck.get(deckCount));
                // 山札を1枚進める
                deckCount++;

                //ディーラーの合計ポイントを計算
                dealerPoint = sumPoint(dealer);
                //ディーラーのバーストチェック
                if(isBusted(dealerPoint)){
                    System.out.println("ディーラーがバーストしました。プレイヤーのかち！");
                    return;
                }
            }
        }
        System.out.println("あなたのポイントは" + playerPoint);
        System.out.println("ディーラーのポイントは" + dealerPoint);

        if(playerPoint == dealerPoint){
            System.out.println("引き分けです。");
        }else if(playerPoint > dealerPoint){
            System.out.println("勝ちました");
        }else{
            System.out.println("負けました");
        }
    }

    private static void shuffleDeck(List<Integer> deck){
        int i = 0;
        // リストに１〜５２の連番を代入
        for(i = 0;i <= 52; i++){
            deck.add(i);
        }

        // 山札をシャッフル
        Collections.shuffle(deck);

        // リストの中身を確認(デバッグ用)
        /*for(i = 0;i < deck.size(); i++){
            System.out.printf(deck.get(i));
        } */
    }

    private static boolean isBusted(int point){
        if(point <= 21){
            return false;
        }else{
            return true;
        }
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
