package doerz.view.layout;

import java.util.LinkedList;

import doerz.model.Post;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/*
 * All of the testing lines are there to make sure
 * things are working and aren't necessarily part of the final design.
 */

public class FeedPane {
	
	/*
	 * MESSAGE_VIEW_CAP determines the number of recent messages to display
	 * on the feed.
	 */
	private static final int MESSAGE_VIEW_CAP = 10;
	
	private Button clrBtn, clrDataBtn;								// testing
	private ScrollPane scrlPane;
	private static VBox feedBox;
	private static LinkedList<Post> feed;
	
	public FeedPane(BorderPane root, Stage stage, LinkedList<Post> feed) {
		FeedPane.feed = feed;
		feedBox = new VBox();
		scrlPane = new ScrollPane();
		scrollPaneSettings();
		
		HBox box = new HBox();
		clrBtn = new Button("Clear Feed View");						// testing
		clrDataBtn = new Button("Remove All Posts");				// testing
		box.getChildren().addAll(clrBtn, clrDataBtn);
		
		callBack();
	
		root.setTop(box);
		root.setBottom(scrlPane);
	}

	private void scrollPaneSettings() {
		scrlPane.setContent(feedBox);
		scrlPane.setHbarPolicy(ScrollBarPolicy.NEVER);
		scrlPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);
        scrlPane.setMaxHeight(500);
	}

	private void callBack() {
		clrBtn.setOnAction(e -> {		// Erases messages from viewport. Does NOT delete data. 
			feedBox.getChildren().clear();
		});
		clrDataBtn.setOnAction(e -> {	// Deletes all currently stored messages. 
			feed.clear();
		});
	}
	
	public static void addToFeed(Post post) {
//		System.out.println(post);
		feed.add(post);
		refresh();
	}
	
	public static void refresh() {
		feedBox.getChildren().clear();	// Clear messages from viewport
		
		/*
		 * lowerBound: Smallest index of messages to be displayed
		 * upperBound: Largest index of messages to be displayed (ie. most recent message)
		 */
		int lowerBound = 0, upperBound = feed.size(); 
		
		if(feed.size() <= MESSAGE_VIEW_CAP) {
			lowerBound = 0;
		} else {
			lowerBound = upperBound - MESSAGE_VIEW_CAP;
		}
		
		/*
		 *  Iterate through message list and display them in order.
		 *  Will only display the most recent messages.
		 *  "Most Recent" is defined by MESSAGE_VIEW_CAP.
		 *  	ie: Display the last 10 messages in the list, 
		 *  		where 10 is MESSAGE_VIEW_CAP.
		 */
		for(Post p : feed.subList(lowerBound, upperBound)) {
			PostView newPost = new PostView(p);
			feedBox.getChildren().add(0, newPost.getPost());
		}
	}

}
