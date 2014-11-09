package cz.cvut.anmchat.app.business.nickname.useCases;

import android.content.Context;

import cz.cvut.anmchat.app.business.AbstractDatabaseUC;
import cz.cvut.anmchat.app.business.BusinessException;
import cz.cvut.anmchat.app.business.nickname.Nickname;
import cz.cvut.anmchat.app.integration.IntegrationException;

/**
 * Created by krejcir on 9.11.14.
 */
public class GenerateNicknameUC extends AbstractDatabaseUC {

    private String[] firstNames;
    private String[] secondNames;

    public GenerateNicknameUC(Context context) {
        super(context);
        this.initFirstNames();
        this.initSecondNames();
    }

    public void generate(String hash) throws BusinessException {
        Nickname n = new Nickname();
        n.setNickname(this.getName());
        n.setHash(hash);
        try {
            this.helper.create(n);
        } catch (IntegrationException e) {
            throw new BusinessException(e.getMessage());
        }
    }

    public String getName() {
        int findex = (int)(Math.random() * this.firstNames.length);
        int sindex = (int)(Math.random() * this.secondNames.length);
        return this.firstNames[findex] + " " + this.secondNames[sindex];
    }

    private void initFirstNames() {
        this.firstNames = new String[]{
                "Red", "Orange", "Yellow", "Green", "Blue", "Violet", "SkyBlue", "Grey", "Orchid", "Salmon", "Wheat", "Snow", "Black",
                "Crazy", "Small", "Smart", "Big", "Great", "Nude", "Naked", "Tall", "Short", "Ugly", "Rude", "Boring", "Swarthy", "Strange",
                "Georgious", "Lucky", "Spiritual"
        };
    }

    private void initSecondNames() {
        this.secondNames = new String[]{
                "Andrew", "George", "Jacob", "Michael", "Stephan", "Orphan", "Luke", "Mountain", "Dinosaurus", "Duck", "Human", "Man", "Woman",
                "Detective", "Farmer", "Smith", "John", "Antelope", "Armando", "Armadillo", "Baboon", "Bat", "Bear", "Pig", "Beaver", "Boar",
                "Buffallo", "Bull", "Calf", "Camel", "Cat", "Pussy", "Dear", "Doe", "Dog", "Dolphin", "Donkey", "Elephant", "Foal", "Fox", "Giraffe",
                "Gnu", "Goat", "Gorilla", "Penguin", "Guinea Pig", "Hamster", "Hare", "Hedgehog", "Horse", "Hyena", "Lamb"
        };
    }
}
