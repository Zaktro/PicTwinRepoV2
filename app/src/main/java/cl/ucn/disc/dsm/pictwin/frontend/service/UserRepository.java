package cl.ucn.disc.dsm.pictwin.frontend.service;

import cl.ucn.disc.dsm.pictwin.frontend.model.User;
import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
//import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

/**
 * A repository of User.
 *
 * @author Matias Canales Benavides.
 */
@Slf4j
@Log
public final class UserRepository {

    /**
     * The API Rest.
     */
    // The local network (access point).
    // private static final String BASE_URL = "http://10.0.0.145:8080";

    // The internal network.
    private static final String Base_URL = "http://192.168.43.14:8080";

    /**
     * The API Rest.
     */
    private final PicTwinAPIRest apiRest;

    /**
     * The Constructor.
     */
    public UserRepository() {
        log.debug("Building UserRepository with URL: {}, BASE_URL");

        // The Logger.
        HttpLoggingInterceptor theLogging = new HttpLoggingInterceptor(log::debug);
        theLogging.setLevel(HttpLoggingInterceptor.Level.BODY);

        //The OkHttp.
        OkHttpClient theClient = new OkHttpClient.Builder()
                .addInterceptor(theLogging)
                .readTimeout(30, TimeUnit.SECONDS)
                .connectTimeout(50,TimeUnit.SECONDS)
                .writeTimeout(50,TimeUnit.SECONDS)
                .callTimeout(50,TimeUnit.SECONDS)
                .build();

        // The Retrofit.
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Base_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(theClient)
                .build();

        // The API Rest.
        this.apiRest = retrofit.create(PicTwinAPIRest.class);
    }

    /**
     * Retrieve a User from PicTwin API Rest.
     *
     * @param email to use.
     * @param password to use.
     * @return the User.
     */
    // @SneakyThrows.
    public Optional<User> retrieveUser(final String email, final String password) {

        //The Call.
        Call<User> cUser = this.apiRest.retrieveUser(email,password);

        try{
            // The Execution.
            Response<User> rUser = cUser.execute();

            // Code in 2xx range.
            if(rUser.isSuccessful()){

                //Check for body.
                if(rUser.body() == null){
                    return Optional.empty();
                }

                //Return the User.
                return Optional.of(rUser.body());
            }

            //If there is an unknown error.
            throw new RuntimeException("Can't Retrieve User", new HttpException(rUser));
        } catch (IOException ex) {
            //I0 error.
            throw  new RuntimeException("Can't Retrieve User", ex);
        }
    }
}