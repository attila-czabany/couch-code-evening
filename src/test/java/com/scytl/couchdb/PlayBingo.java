package com.scytl.couchdb;

import org.ektorp.changes.ChangesCommand;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.scytl.couchdb.feed.BallFeed;
import com.scytl.couchdb.model.BingoCard;
import com.scytl.couchdb.model.User;
import com.scytl.couchdb.repository.BingoCardRepository;
import com.scytl.couchdb.spring.CouchDbCrudContext;
import com.scytl.couchdb.spring.CouchDbIntegrationContext;

public class PlayBingo {

	public static ApplicationContext CONTEXT;

	@BeforeClass
	public static void setup() {
		final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.registerShutdownHook();
		context.register(CouchDbCrudContext.class);
		context.register(CouchDbIntegrationContext.class);
		context.refresh();

		CONTEXT = context;
	}

	@Test
	public void testCouch() {

		User user = registerUserIfDoesNotExist();

		if (user != null) {
			System.out.println("You are user: " + user.getId());
		}

		BingoCard card = askForCard();
		if (card != null) {
			System.out.println("You picked card #" + card.getId());
		}

		card = reportCardId(user, card);
		if (card != null) {
			System.out
					.println("The following user is registered to this card: "
							+ card.getUserid());
		}

		BallFeed feed = getFeedFromSpring();
		if (feed != null) {
			feed.setCommand(createFeedCommand());
		}
		startListeningForTheBalls(feed);

		// shout if you won!

	}

	private User registerUserIfDoesNotExist() {
		// TODO implement
		return null;
	}

	private BingoCard askForCard() {
		BingoCardRepository bingoCardRepository = CONTEXT
				.getBean(BingoCardRepository.class);
		return bingoCardRepository.getOneNonAssigned();
	}

	private BingoCard reportCardId(User user, BingoCard card) {
		// TODO implement
		return null;
	}

	private BallFeed getFeedFromSpring() {
		return CONTEXT.getBean(BallFeed.class);
	}

	private ChangesCommand createFeedCommand() {
		// TODO implement
		return null;
	}

	private void startListeningForTheBalls(BallFeed feed) {
		feed.run();
	}
}
