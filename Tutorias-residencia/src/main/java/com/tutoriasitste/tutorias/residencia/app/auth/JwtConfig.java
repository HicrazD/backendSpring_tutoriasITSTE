package com.tutoriasitste.tutorias.residencia.app.auth;

public class JwtConfig {
	public static final String LLAVE_SECRETA = "alguna.clave.secreta.12345678";
	
	public static final String RSA_PRIVADA = "-----BEGIN RSA PRIVATE KEY-----\r\n"
			+ "MIIEpQIBAAKCAQEAs6ky0d6StB8Q4P1r5C/BIjd2M7SSB8dOB/pTYTzZSyK1Wcm8\r\n"
			+ "J44Z23f5BRGk8HTPK7mJzjj0bxTq3teGJAtOdWqIhJHKZhJgphwrzTM4V5rbnzm/\r\n"
			+ "CsgqytMEuKnSUI6CahjjYfVV3f7jo3bF76luRg4XngcjQJlVfO2WtSM+fG3nRucm\r\n"
			+ "tn+EtDxp93rA+UOgHf0E6YjLmv/Dg89NEwXvq/P6YZ8xL4CwBPT9Rk3ExrDDieqM\r\n"
			+ "YFH2KhytACUHTD/pRuuDFKx2Ou++OITApFvBxWsmidrjWooPycdvDQfY8TmmPjSF\r\n"
			+ "gzgZ09bgfVPh5a59QP6/L80NSO6rkUiqLPg2EQIDAQABAoIBACe1bOMP85LWDu/7\r\n"
			+ "cx5DI8fBRGHXNjN77JY80LL8NPazXCvVN73yXWqxkJt7M95louiCIKAw3G20zNim\r\n"
			+ "ACL1UMkI29WZN4fp5eyw60l2WaybMJF3vO7QIQ0lKLT7690cMlzq1U7wX6MqU/vs\r\n"
			+ "I4F+DD51nOizoSoE+e0bwiSU/pmXfeQwgCnqtP9g0ROlZPsNGZcTAkvme2p+dDR8\r\n"
			+ "Hy3jKxdLV2P8Ebe268N12q0Hv19txRJfJ6Ku5dFX2JDyIUOumhXmse4r1m4hu4mf\r\n"
			+ "T3KffUcS4fTPhrD/Nicv/Aqluks00lVXIClqzYBroUndmqYzT5iTGFi9/2PCstd2\r\n"
			+ "uB+ZMbUCgYEA52j1N2AEuNDPljftvLUO6OI1WHw2YZlajWV11IakybHP7a+w1T0P\r\n"
			+ "VLxnBxNYpGA6tIVmxRmk/91H1rn2yg3ZkzLpFlrWo3cap49hgTbqLW78DfGmfVra\r\n"
			+ "s1w5T5unkKelA/RJULi6AvVlrTCVrqQVvquK9zOgHfe8v8M8Ef0tbVcCgYEAxsCD\r\n"
			+ "BxnqPyu7AafcLsNh91UB0ApoUtZCYwKdM5Ychc/kxDplux9W+rV7mLnnkFvEJhi/\r\n"
			+ "MkqtlumHurUNNC47HG+mdHynYm7bA9UKjoR0o4JZhCkeVUU7VQiMhzXSFTHTT+jv\r\n"
			+ "uMRKLw9Xoc2aHp35/MdZkqZCPycGStmP9/QabtcCgYEApDgLrNxq/WzRo9zUzVH/\r\n"
			+ "3qyJBt4/CUv2wtj5oXRbhPa2hApmBy1pr8xzoMXN5fiUMH9qyvzD5v91d6BIzmK9\r\n"
			+ "wIQkfUTBhQeE1twdacE6tjtIi+ytJdBP23DbCu9/HlyZ8hBt5ubwuiDDowATePfg\r\n"
			+ "poq992effSTNwZgfDPUKQ8kCgYEAhA31AQre8QoFPpxhV1DKDqOUiL3hguiJQ2/B\r\n"
			+ "Mz1RxjbTMO38GdBA4mMis98RVIEkWoumvauQmuyfGhahp0HwVHx6hHsuW+B79Jfg\r\n"
			+ "Y8DuHpXFFGKdnNpbCayccln4M8zfMgD7BjCleY8gEomhFSaxqpppkV6WNY+gRqs3\r\n"
			+ "VPADgBECgYEAgeU1ZKgZFoeNSnc5YlVoxXQPy+f2MLDzwgGeJuKU0pP0zpglG4Zh\r\n"
			+ "XG/s9Ja2PforazucSXtz9AnCwfAgDf55O+VsD1FZVIFu8WJuJBpTLzAf+ClNq/Z2\r\n"
			+ "pfzbo7R6ypNVMrwmFDuCgWtaP3QKakjLYQ52s2uSLfD6jPmflFAIjZA=\r\n"
			+ "-----END RSA PRIVATE KEY-----";
	
	public static final String RSA_PUBLICA = "-----BEGIN PUBLIC KEY-----\r\n"
			+ "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAs6ky0d6StB8Q4P1r5C/B\r\n"
			+ "Ijd2M7SSB8dOB/pTYTzZSyK1Wcm8J44Z23f5BRGk8HTPK7mJzjj0bxTq3teGJAtO\r\n"
			+ "dWqIhJHKZhJgphwrzTM4V5rbnzm/CsgqytMEuKnSUI6CahjjYfVV3f7jo3bF76lu\r\n"
			+ "Rg4XngcjQJlVfO2WtSM+fG3nRucmtn+EtDxp93rA+UOgHf0E6YjLmv/Dg89NEwXv\r\n"
			+ "q/P6YZ8xL4CwBPT9Rk3ExrDDieqMYFH2KhytACUHTD/pRuuDFKx2Ou++OITApFvB\r\n"
			+ "xWsmidrjWooPycdvDQfY8TmmPjSFgzgZ09bgfVPh5a59QP6/L80NSO6rkUiqLPg2\r\n"
			+ "EQIDAQAB\r\n"
			+ "-----END PUBLIC KEY-----";

}
