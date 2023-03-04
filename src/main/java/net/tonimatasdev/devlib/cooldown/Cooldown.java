package net.tonimatasdev.devlib.cooldown;

public class Cooldown {
    private long lastUsed;
    private final long cooldownTime;
    private final TimeUnit timeUnit;

    public Cooldown(long cooldownTime, TimeUnit timeUnit) {
        this.cooldownTime = cooldownTime;
        this.timeUnit = timeUnit;
    }

    public boolean isOnCooldown() {
        switch (timeUnit) {
            case HOURS: return System.currentTimeMillis()/3600000 - lastUsed < cooldownTime;
            case MINUTES: return System.currentTimeMillis()/60000 - lastUsed < cooldownTime;
            case SECONDS: return System.currentTimeMillis()/1000 - lastUsed < cooldownTime;
            case MILLIS: return System.currentTimeMillis() - lastUsed < cooldownTime;
            default: return false;
        }
    }

    public void use() {
        switch (timeUnit) {
            case HOURS: lastUsed = System.currentTimeMillis()/3600000; break;
            case MINUTES: lastUsed = System.currentTimeMillis()/60000; break;
            case SECONDS: lastUsed = System.currentTimeMillis()/1000; break;
            case MILLIS: lastUsed = System.currentTimeMillis(); break;
        }
    }

    public long getCooldownTime() {
        return cooldownTime;
    }

    public long getLastUsed() {
        return lastUsed;
    }

    public TimeUnit getTimeType() {
        return timeUnit;
    }
}
