package be.ugent.zeus.hydra.ui.main.homefeed;

import be.ugent.zeus.hydra.ui.main.homefeed.content.HomeCard;
import be.ugent.zeus.hydra.repository.requests.Request;
import java8.util.stream.Stream;

/**
 * A request that provides {@link HomeCard}s for use in the home feed.
 *
 * @author Niko Strijbol
 */
public interface HomeFeedRequest extends Request<Stream<HomeCard>> {

    /**
     * @return The card type of the cards that are produced here.
     */
    @HomeCard.CardType
    int getCardType();
}