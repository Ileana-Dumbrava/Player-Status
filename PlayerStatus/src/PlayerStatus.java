
public class PlayerStatus {

	private String nickname;
	private int score;
	private int lives;
	private int health;
	private String weaponInHand;
	private double positionX;
	private double positionY;
	private static String gameName;
	
	public void initPlayer(String nickname) {
		this.nickname = nickname;
	}
	
	public void initPlayer(String nickname, int lives) {
		this.nickname = nickname;
		this.lives = lives;
	}
	
	public void initPlayer(String nickname, int lives, int score) {
		this.nickname = nickname;
		this.lives = lives;
		this.score = score;
	}
		
	public String getNickname() {
		return this.nickname;
	}
		
	public int getHealth() {
		return this.health;
	}
	
	public void setHealth(int health) {
		this.health += health;
		if (this.health <= 0) {
			this.lives--;
			this.health = 100;
		}
		if (this.health >= 100) {
			this.health = 100;
		}
	}
	
	public void checkLives() {
		if (this.lives == 0) {
			System.out.println("Game over!");
		}
	}
	
	public int getScore() {
		return this.score;
	}
		
	private boolean isPrime(int artifactCode) {
		if (artifactCode == 1) {
			return false;
		}
		for (int i = 2; i <= artifactCode/2; i++) {
			if (artifactCode % i == 0) {
				return false;
			}
		}
		return true;
	}
	
	private boolean isPerfect(int artifactCode) {
		int sumOfDivisors = 0;
		for (int i = 1; i <= artifactCode/2; i++) {
			if (artifactCode % i == 0) {
				sumOfDivisors += i;
			}
		}
		if (artifactCode == sumOfDivisors) {
			return true;
		}		
		return false;
	}
	
	private boolean isEven(int artifactCode) {
		if (artifactCode % 2 == 0) {
			return true;
		}
		return false;
	}
	
	private boolean sumOfDigitsDivBy3(int artifactCode) {
		int sumOfDigits = 0;
		while (artifactCode != 0) {
			sumOfDigits += artifactCode % 10;
			artifactCode /= 10;
		}		
		if (sumOfDigits % 3 == 0) {
			return true;
		}
		return false;
	}
	
	public void findArtifact(int artifactCode) {
		if (isPerfect(artifactCode)) {
			this.score += 5000;
			this.lives += 1;
			this.health = 100;
		} else if (isPrime(artifactCode)) {
			this.score += 1000;
			this.lives += 2;
			this.setHealth(25);
		} else if (isEven(artifactCode) && sumOfDigitsDivBy3(artifactCode)) {
			this.score -= 3000;
			this.setHealth(-25);			
		} else {
			this.score += artifactCode;
		}
	}
	
	public String getWeaponInHand() {
		return this.weaponInHand;
	}
	
	public int checkWeaponPrice(String weapon) {
		if (weapon.equalsIgnoreCase("knife")) {
			return 1000;
		} else if (weapon.equalsIgnoreCase("sniper")) {
			return 10000;
		} else if (weapon.equalsIgnoreCase("kalashnikov")) {
			return 20000;
		} else {
		System.out.println("The weapon checked is not available");
		return -1;
		}
	}
			
	public boolean setWeaponInHand(String weaponInHand) {
		int weaponPrice = checkWeaponPrice(weaponInHand);
		if (weaponPrice != -1 && this.getScore() >= weaponPrice) {
			this.weaponInHand = weaponInHand;
			this.score -= weaponPrice;
			System.out.println("Your new weapon is: " + this.weaponInHand 
					+ " and your new score is: " + this.score);
			return true;
		}
		return false;
	}
	
	public double getPositionX() {
		return this.positionX;
	}
	
	public void setPositionX(double positionX) {
		this.positionX = positionX;
	}
	
	public double getPositionY() {
		return this.positionY;
	}
	
	public void setPositionY(double positionY) {
		this.positionY = positionY;
	}
	
	public void movePlayerTo(double positionX, double positionY) {
		this.positionX = positionX;
		this.positionY = positionY;
	}
	
	public String getGameName() {
		return gameName;
	}
	
	void setGameName(String newGameName) {
		gameName = newGameName;
	}
	
	private double getDistance(PlayerStatus opponent) {
		double distance = 0;
		distance = Math.sqrt(Math.pow(this.positionX - opponent.getPositionX(), 2) 
				+ Math.pow(this.positionY - opponent.getPositionY(), 2));			
		return distance;
	}
	
	public int getProbabilityToWinDuel() {
		return (3 * this.health + this.score / 1000) / 4;
	}
	
	public boolean isWeaponGoodForLongDistance(PlayerStatus opponent) {
		if (this.weaponInHand.equalsIgnoreCase("sniper")) {
			return true;
		} else if (this.weaponInHand.equalsIgnoreCase("kalashnikov") 
				&& opponent.getWeaponInHand().equalsIgnoreCase("knife")) {
			return true;
		}
		return false;
	}
	
	public boolean isWeaponGoodForShortDistance(PlayerStatus opponent) {
		if (this.weaponInHand.equalsIgnoreCase("kalashnikov")) {
			return true;
		} else if (this.weaponInHand.equalsIgnoreCase("sniper") 
				&& opponent.getWeaponInHand().equalsIgnoreCase("knife")) {
			return true;
		}
		return false;
	}
	
	public boolean shouldAttackOpponent(PlayerStatus opponent) {
		String opponentWeapon = opponent.getWeaponInHand();
		if (this.weaponInHand.equalsIgnoreCase(opponentWeapon)) {
			if (this.getProbabilityToWinDuel() > opponent.getProbabilityToWinDuel()) {
				return true;
			}
		} else {
			double distance = this.getDistance(opponent);
			if (distance > 1000){
				if(this.isWeaponGoodForLongDistance(opponent)) {
					return true;
				}
			} else {
				if(this.isWeaponGoodForShortDistance(opponent)) {
					return true;
				}
			}		
		}
		
		return false;
	}
	
	public void displayPlayerDetails() {
		System.out.println("Player " + this.nickname + " has: \n\t-score " + this.score 
				+ "\n\t-lives " + this.lives + "\n\t-health " + this.health
				+ "\n\t-weapon " + this.weaponInHand);
	}
	
}
