
public class Main {

	public static void main(String[] args) {

		PlayerStatus player1 = new PlayerStatus();	
		PlayerStatus player2 = new PlayerStatus();
		player1.initPlayer("Raiden", 3, 5000);
		player2.initPlayer("Sub-Zero", 3, 5000);

		player1.movePlayerTo(2, 4);
		player2.movePlayerTo(6, 3);
		
		player1.findArtifact(6);
		player2.findArtifact(13);
		
		System.out.println(player1.getScore());
		System.out.println(player2.getScore());
		
		player1.displayPlayerDetails();
		player2.displayPlayerDetails();
		
		player1.setWeaponInHand("sniper");
		player2.setWeaponInHand("knife");
		
		player1.displayPlayerDetails();
		player2.displayPlayerDetails();
		
		System.out.println(player1.shouldAttackOpponent(player2));
		System.out.println(player2.shouldAttackOpponent(player1));
		
		
	}

}
