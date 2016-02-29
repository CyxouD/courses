package com.rxn1d.courses;
public class RealTablePlayer extends AbstractPlayer{
	private boolean madeBet;
	
	public RealTablePlayer(double balance, String name) {
		super(balance, name);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeBet(String bet) {
		double playerBet = Double.parseDouble(bet.split(" ")[0]);
		if (madeBet || playerBet < 1 || playerBet > 500){
			System.out.println("BET NOT ACCEPTED");
			return;
		}
		if (playerBet > balance){
			inGame = false;
			System.out.println("NOT ENOUGH BALANCE FOR "+name);
			return;
		}
		this.bet = playerBet;
		String[] betString = bet.split(" ");
			if (betString.length == 2)
				this.betType = new BetType(betString[1]);
			else
				this.betType = new BetType(betString[1] + betString[2]);
		madeBet = true;
		System.out.println("BET ACCEPTED");
		
	}
	
	public boolean isMadeBet() {
		return madeBet;
	}

	
	public void setMadeBet(boolean madeBet) {
		this.madeBet = madeBet;
	}

    public boolean equals(Object o){
        if (o == null) return false;

        if (!(o instanceof RealTablePlayer)) return false;
        RealTablePlayer that = (RealTablePlayer) o;

        if (this.name.equals(that.name)) return true;
        return false;

    }

    public int hashCode(){
		return name.hashCode();
	}
	
}
