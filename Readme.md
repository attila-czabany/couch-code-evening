Steps
======

Include all the code in `PlayBingo` class.
Add test class method name in each section.

Register user
-------------

For user registration, we need a crud. The crud works if we extend `CouchDbRepositorySupport<Class>` abstract class (`UserRepository` should be used as a base). We should include in the constructor the calling of the `initStandardDesignDocument()` function as well (this will come in handy later, explanation will come).

The spring context is available in the CONTEXT variable. You can reference the repository bean by:

```java
UserRepository userRepository = CONTEXT.getBean(UserRepository.class);
```

Please set the ID of the user according to your name. The rest of the fields are oprtional in this example.

If you want to query an user by id, the `repository.get("userid")` will be your call. It will throw a `DocumentNotFoundException` if the user does not exist. Similarly, if you want to update an user and you have a conflict, or you want to register the same user (the same id) again, you will get an `UpdateConflictException`.


Get one of the Bingo cards
-------------

Similarly to the `UserRepository`, we need to extend the crud that is called `BingoCardRepository`.

It needs the same functionality as the UserRepository. We need to implement the method

```java
public BingoCard getOneNonAssigned();
```

The implementation of the function is as follows:

```java
List<BingoCard> queryView = queryView("nonAssigned");
```

Since we would like to use a view on the database, we have to include the view in our code as an annotation on the repository:

```java
@View( name="nonAssigned", map = "function(doc) { if(!doc.userid) emit( null, doc._id )}")
```

This will create a view, where only the documents will be returned, that does not have userid defined.

Pick one from the returned list (first, last, whatever, you have 60 :) ).

== Last sentence it's like disconnnected from the queryView snippet. ==


Mark that card as yours
-------------

You need to update the card that you retrieved, and set your userid for that card. You MUST not have more than 1 bingo card (I'm checking with a query just to make sure :) )

To do this, we need to add another view to the repository. That can be done like this:

```java
@Views ( value = {@View( name="nonAssigned", map = "function(doc) { if(!doc.userid) emit( null, doc._id )}"), @View( name="assignedtoUser", map = "function(doc, req) { emit( doc.userid, doc._id )}")})
```

Since we would like to query only the bingo cards that are belonging to us, we need to pass some extra parameters.

```java
	public BingoCard getAssignedCard(String userid){
		List<BingoCard> queryView = db.queryView(createQuery("assignedtoUser")
		    .key(userid).includeDocs(true),type);
		if(queryView.size() == 1){
			return queryView.get(0);
		}
		return null;
	}
```

Explanation about feeds
-------------

TBD


Listening for Bingo Balls
-------------
For the feeds to work, we need to have the annotation in place for the filter to be registered. The following annotation must be present on the `BallRepository` class.

```java
@Filters( value ={@Filter( name = "codeEveningGame", function = "function(doc, req) { if(doc.game == req.query.game) { return true; } return false; }")})
```

And as usual, the `BallRepository` has to act as crud as well.

The feed can be run with the following code, that should be added to the run() method of the BallFeed class:

```java
ChangesFeed feed = db.changesFeed(command);
		try{
			while(feed.isAlive()) {
				try {
					DocumentChange documentChange = feed.next(10, TimeUnit.SECONDS);
					if(documentChange != null) {
						String doc = documentChange.getDoc();
						Ball ball = objectMapper.readValue(doc, Ball.class);
						System.out.println(ball.getNumber());
					}
				} catch (InterruptedException e) {
					System.out.println("feed killed");
				}
			}
		} catch (Exception e){
			feed.cancel();
		}
```

This will implement the feed, and the balls will be printed on the screen.

However, we need 1 more thing before the feed can actually start, that is to specify the command for the feed. There is a createFeedCommand() function, that should have the following code:

```java
ChangesCommand.Builder builder = new ChangesCommand.Builder();
		builder=builder.includeDocs(true).since(0).filter("Ball/codeEveningGame").param("game", "bingo");
		return builder.build();
```