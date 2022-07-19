package cl.ucn.disc.dsm.pictwin.frontend.service;

import cl.ucn.disc.dsm.pictwin.frontend.model.User;
import cl.ucn.disc.dsm.pictwin.frontend.model.Pic;
import cl.ucn.disc.dsm.pictwin.frontend.model.Twin;

/**
 * The PicTwins operations.
 * @author Matías Canales Benavides.
 */

public interface Pictwin {

    /**
     * Create a User with a specific password.
     *
     * @param user to create
     * @param password to hash.
     * @return the User created.
     */

    User create (User user, String password);

    /**
     * Return authenticate User with a specific password and email.
     *
     * @param email to create
     * @param password to hash.
     * @return the User authenticate.
     */
    User authenticate (String email, String password);

    /**
     * Create a Twin with a specific pic and idUser.
     *
     * @param pic to pic
     * @param idUser to id user.
     * @return the twin created.
     */

    Twin createTwin( Pic pic, Long idUser);

    /**
     * Do a dislike with a pic in a twin.
     *
     * @param idTwin to dislike
     * @param idUser to id user the Twin.
     */
    void dislike (Long idTwin, Long idUser);

    /**
     * @return the number of users in the system
     */

    Long getUserSize();

}
