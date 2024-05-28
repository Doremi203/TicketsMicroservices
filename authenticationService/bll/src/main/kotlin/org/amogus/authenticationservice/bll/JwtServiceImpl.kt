package org.amogus.authenticationservice.bll

import io.jsonwebtoken.Claims
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.amogus.authenticationservice.domain.interfaces.services.JwtService
import org.amogus.authenticationservice.domain.models.User
import org.amogus.authenticationservice.bll.exceptions.IllegalJwtTokenException
import java.util.*
import javax.crypto.SecretKey

class JwtServiceImpl(
    private val secret: String,
    expirationTimeInMinutes: Int
) : JwtService {
    private val expirationTimeInMillis = 1000 * 60 * expirationTimeInMinutes

    override fun isTokenValid(jwtToken: String, user: User): Boolean {
        val userName = extractUserName(jwtToken)
        return userName == user.username
    }

    override fun generateToken(user: User): String {
        return generateToken(HashMap(), user)
    }

    override fun generateToken(extraClaims: Map<String, Any>, user: User): String {
        val time = System.currentTimeMillis()
        return Jwts
            .builder()
            .claims(extraClaims)
            .subject(user.username)
            .issuedAt(Date(time))
            .expiration(Date(time + expirationTimeInMillis))
            .signWith(getSecretKey(), Jwts.SIG.HS256)
            .compact()
    }

    override fun extractUserName(jwtToken: String): String? {
        return extractClaim(jwtToken, Claims::getSubject)
    }

    private fun <T> extractClaim(jwtToken: String, claimsResolver: (Claims) -> T): T {
        val claims = extractClaims(jwtToken)
        return claimsResolver(claims)
    }

    private fun extractClaims(jwtToken: String): Claims {
        return try {
            Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .payload
        }
        catch (e: ExpiredJwtException) {
            throw IllegalJwtTokenException("JWT token has expired")
        }
        catch (e: Exception) {
            throw IllegalJwtTokenException("Incorrect JWT token")
        }
    }

    private fun getSecretKey(): SecretKey {
        val bytes = Decoders.BASE64.decode(secret)
        return Keys.hmacShaKeyFor(bytes)
    }
}
