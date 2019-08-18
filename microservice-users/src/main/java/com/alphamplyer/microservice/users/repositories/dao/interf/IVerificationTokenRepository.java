package com.alphamplyer.microservice.users.repositories.dao.interf;

import com.alphamplyer.microservice.users.models.VerificationToken;

public interface IVerificationTokenRepository {

    VerificationToken getVerificationToken(String token);

    void save(VerificationToken verificationToken);

}
