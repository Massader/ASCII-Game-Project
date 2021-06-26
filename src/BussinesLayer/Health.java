package BussinesLayer;

public class Health {

    private int pool;
    private int amount;

    public Health(int pool){
        this.pool=pool;
        this.amount=pool;
    }

    public int getPool() {
        return pool;
    }
    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPool(int pool) {
        this.pool = pool;
    }

    public void heal(int healed) {
        amount += healed;
        if (amount > pool) amount = pool;
    }

    public boolean takeDamage(int damage){
        amount = Math.max(0, amount - damage);
        return (amount <= 0);
    }

    @Override
    public String toString() {
        return amount + "/" + pool;
    }
}
