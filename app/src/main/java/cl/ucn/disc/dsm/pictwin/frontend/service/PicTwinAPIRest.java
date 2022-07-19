package cl.ucn.disc.dsm.pictwin.frontend.service;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import cl.ucn.disc.dsm.pictwin.frontend.model.User;

/**
 * The API REST declaration of PicTwin.
 *
 * @author Matias Canales Benavides.
 */
public interface PicTwinAPIRest {

    /**
     * Retrieve the User.
     *
     * @param email    to use.
     * @param password to use
     * @return the User.l
     */
    @GET("/users")
    Call<User> retrieveUser(@Query("email") String email, @Query(value = "password", encoded = true) String password);

}
