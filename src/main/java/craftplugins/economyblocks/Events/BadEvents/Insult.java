package craftplugins.economyblocks.Events.BadEvents;

import craftplugins.economyblocks.BankHandler;
import craftplugins.economyblocks.Events.CarePackageEvent;
import craftplugins.economyblocks.Utils;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Insult implements CarePackageEvent {
    @Override
    public void runEvent(Player player, BankHandler bankHandler) {

        System.out.println(player.getName() + " has opened a care package : " + this.getClass().getSimpleName());

        List<String> insults = new ArrayList<>();

        String insultsString = "You lack brains so much that you can float on water.\n" +
                "It would help if you were the poster child of a condom company.\n" +
                "You're about as useful as the white crayon.\n" +
                "Did you use a mud puddle as a mirror this morning?\n" +
                "Were you carrying an umbrella when God was giving out beauty?\n" +
                "Explaining to you is like teaching calculus to a lemur.\n" +
                "If you ran like your mouth, maybe you'd win a gold medal.\n" +
                "You're as sharp as a marble.\n" +
                "There's some shit on your clothes. Oh, nope. That's just you.\n" +
                "You're as useful as a condom with holes on it.\n" +
                "You look like you have more craters than the moon.\n" +
                "Wanna know what's the difference between you and eggs? Eggs get laid, and you don't.\n" +
                "Why do you play so hard to get for someone hard to want?\n" +
                "I will call you pussy. But you lack warmth and depth.\n" +
                "If opposites attract, then I hope you meet someone who is attractive, honest, intelligent, and cultured.\n" +
                "You should carry a plant around with you to replace the oxygen you waste.\n" +
                "You look like the two guys from 21st Jump Street combined.\n" +
                "You should stop using your head as a container for your teeth.\n" +
                "You are living proof that even ugly people have sex.\n" +
                "You look like the type of person who can't spell DNA.\n" +
                "Some drink from the fountain of knowledge. You only gargled.\n" +
                "Do you only use your head to get haircuts?\n" +
                "Had your parents not heard of contraception?\n" +
                "If you ate trash, it would be cannibalism.\n" +
                "Everyone needs love, but you pay for it.\n" +
                "You look like a stepped-on sandcastle.\n" +
                "I see that you've set aside this special time to humiliate yourself in public.\n" +
                "You look like a garden gnome.\n" +
                "You are a complete tool, but you're not even useful.\n" +
                "How are your parents related?\n" +
                "If you were the light at the end of the tunnel, I'd turn back around.\n" +
                "If I throw a stick, will you leave?\n" +
                "What kind of contraception do you use? Your face?\n" +
                "I'll give you a +1 so you can invite your friend... if you have one.\n" +
                "I bet your parents change the subject when their friends ask about you.\n" +
                "I'd say you're dumb as a rock, but at least a rock can hold a door open.\n" +
                "Why don't you go take a long walk off a short pier?\n" +
                "You look like someone just hit Random on the customization screen.\n" +
                "If ignorance is bliss, then you must be one happy idiot.\n" +
                "You're so far behind you think you're first.\n" +
                "When it was raining brains, were you holding an umbrella?\n" +
                "You could hide your own Easter eggs and forget where they are.\n" +
                "If you had another brain, it would be lonely.\n" +
                "Keep rolling your eyes. Maybe you'll find a brain back there.\n" +
                "You look like you eat buttons off the remote control.\n" +
                "If laughter is the best medicine, your face must be curing the world.\n" +
                "You're so ugly, you scared the crap out of the toilet.\n" +
                "No I'm not insulting you, I'm describing you.\n" +
                "It's better to let someone think you are an Idiot than to open your mouth and prove it.\n" +
                "If I had a face like yours, I'd sue my parents.\n" +
                "Your birth certificate is an apology letter from the condom factory.\n" +
                "I guess you prove that even god makes mistakes sometimes.\n" +
                "The only way you'll ever get laid is if you crawl up a chicken's ass and wait.\n" +
                "You're so fake, Barbie is jealous.\n" +
                "I’m jealous of people that don’t know you!\n" +
                "You're so ugly, when your mom dropped you off at school she got a fine for littering.\n" +
                "If I wanted to kill myself I'd climb your ego and jump to your IQ.\n" +
                "You must have been born on a highway because that's where most accidents happen.\n" +
                "Brains aren't everything. In your case they're nothing.\n" +
                "I don't know what makes you so stupid, but it really works.\n" +
                "Your family tree must be a cactus because everybody on it is a prick.\n" +
                "I can explain it to you, but I can’t understand it for you.\n" +
                "Roses are red violets are blue, God made me pretty, what happened to you?\n" +
                "Behind every fat woman there is a beautiful woman. No seriously, your in the way.\n" +
                "Calling you an idiot would be an insult to all the stupid people.\n" +
                "You, sir, are an oxygen thief!\n" +
                "Some babies were dropped on their heads but you were clearly thrown at a wall.\n" +
                "Why don't you go play in traffic.\n" +
                "I'd slap you, but that would be animal abuse.\n" +
                "They say opposites attract. I hope you meet someone who is good-looking, intelligent, and cultured.\n" +
                "Stop trying to be a smart ass, you're just an ass.\n" +
                "The last time I saw something like you, I flushed it.\n" +
                "I'm busy now. Can I ignore you some other time?\n" +
                "You have Diarrhea of the mouth; constipation of the ideas.\n" +
                "If ugly were a crime, you'd get a life sentence.\n" +
                "Your mind is on vacation but your mouth is working overtime.\n" +
                "Why don't you slip into something more comfortable... like a coma.\n" +
                "Shock me, say something intelligent.\n" +
                "You are not as bad as people say, you are much, much worse.\n" +
                "Don't like my sarcasm, well I don't like your stupid.\n" +
                "You're the reason the gene pool needs a lifeguard.\n" +
                "Sure, I've seen people like you before - but I had to pay an admission.\n" +
                "How old are you? - Wait I shouldn't ask, you can't count that high.\n" +
                "Have you been shopping lately? They're selling lives, you should go get one.\n" +
                "You're like Monday mornings, nobody likes you.\n" +
                "Of course I talk like an idiot, how else would you understand me?\n" +
                "All day I thought of you... I was at the zoo.\n" +
                "You're so fat, you could sell shade.\n" +
                "I'd like to see things from your point of view but I can't seem to get my head that far up my ass.\n" +
                "Don't you need a license to be that ugly?\n" +
                "My friend thinks he is smart. He told me an onion is the only food that makes you cry, so I threw a coconut at his face.\n" +
                "Your house is so dirty you have to wipe your feet before you go outside.\n" +
                "If you really spoke your mind, you'd be speechless.\n" +
                "Stupidity is not a crime so you are free to go.\n" +
                "If I told you that I have a piece of dirt in my eye, would you move?\n" +
                "You so dumb, you think Cheerios are doughnut seeds.\n" +
                "So, a thought crossed your mind? Must have been a long and lonely journey.\n" +
                "Every time I'm next to you, I get a fierce desire to be alone.\n" +
                "You're so dumb that you got hit by a parked car.\n" +
                "You're so fat, you leave footprints in concrete.\n" +
                "How did you get here? Did someone leave your cage open?\n" +
                "Pardon me, but you've obviously mistaken me for someone who gives a damn.\n" +
                "Wipe your mouth, there's still a tiny bit of bullshit around your lips.\n" +
                "Just because you have one doesn't mean you have to act like one.\n" +
                "Are you always this stupid or is today a special occasion?\n" +
                "You lack so much brain matter that you'd float on water.\n" +
                "I'd insult you, but then I'll have to explain it afterward, so never mind.\n" +
                "I don't have the time or the crayons to explain it to you.\n" +
                "In what way are your parents related to each other?\n" +
                "You know nothing. In fact, you know less than nothing because if you knew that you knew nothing, that would be something.\n" +
                "I expected an intellectual conversation, but it seems there's no one around to have that with.\n" +
                "How can your IQ be in single numbers?\n" +
                "I'd have to understand this for you. Explaining won't help you either.\n" +
                "I'm jealous of how you can be so dumb.\n" +
                "Don't ever wear a burlap sack on your head. People won't be able to tell where the sack started and where your face ended.\n" +
                "You won't be able to get a dime as a prostitute on a half-price day.\n" +
                "Nice face. I bet you'd look good on the radio.\n" +
                "What contraceptive do you use? Your face?\n" +
                "When God rained beauty all over his creations, you were probably holding an umbrella.\n" +
                "Taking a picture of you would put a virus on my phone.\n" +
                "You do realize we tolerate you.\n" +
                "Calling you is a waste of time.\n" +
                "I'd be happy to hear from you if you were actually important.\n" +
                "My headaches left immediately I left your presence.\n";


        Scanner scanner = new Scanner(insultsString);

        while (scanner.hasNextLine()) {
            insults.add(scanner.nextLine());

        }

        int randomNum = Utils.getRandomNumber(0, insults.size());

        String randomInsult = insults.get(randomNum);

        player.sendMessage(Utils.chat("&d" + randomInsult));



    }
}
