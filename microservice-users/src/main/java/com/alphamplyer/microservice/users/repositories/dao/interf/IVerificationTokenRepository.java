package com.alphamplyer.microservice.users.repositories.dao.interf;

import com.alphamplyer.microservice.users.models.VerificationToken;

public interface IVerificationTokenRepository {

    /**
     * Get verification token by its token
     * @param token verification token token
     * @return a verification token or null
     */
    VerificationToken getVerificationToken(String token);

    /**
     * Insert a verification token
     * @param verificationToken verification token data
     */
    void add(VerificationToken verificationToken);

}
