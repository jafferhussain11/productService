package dev.jaffer.productService.elasticsearch;
//
//import java.io.File;
//
//import javax.net.ssl.SSLContext;
//
//import org.apache.http.auth.AuthScope;
//import org.apache.http.auth.UsernamePasswordCredentials;
//import org.apache.http.client.CredentialsProvider;
//import org.apache.http.impl.client.BasicCredentialsProvider;
//import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
//import org.apache.http.ssl.SSLContextBuilder;
//import org.apache.http.ssl.SSLContexts;
//import org.elasticsearch.client.RestClientBuilder.HttpClientConfigCallback;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class HttpClientConfigImpl implements HttpClientConfigCallback {
//
//    @Override
//    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
//        try {
//            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
//            UsernamePasswordCredentials usernamePasswordCredentials = new UsernamePasswordCredentials("elastic",
//                    "mCF37aDQTftLyI2oJHj");
//            credentialsProvider.setCredentials(AuthScope.ANY, usernamePasswordCredentials);
//            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
//
//            String trustLocationStore = "/Users/jafferhussain/Downloads/elasticsearch-8.11.4/config/certs/truststore.p12";
//            File trustLocationFile = new File(trustLocationStore);
//
//            SSLContextBuilder sslContextBuilder = SSLContexts.custom().loadTrustMaterial(trustLocationFile,
//                    "javainuse".toCharArray());
//            SSLContext sslContext = sslContextBuilder.build();
//            httpClientBuilder.setSSLContext(sslContext);
//
//        } catch (Exception e) {
//        }
//        return httpClientBuilder;
//    }
//}
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ElasticSearchConfig extends ElasticsearchConfiguration {

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .usingSsl("e881798de4015501b5d144f29840e97cf0f75ccca1f26febfcf422576b49ce23")
                .withBasicAuth("elastic", "mCF37aDQTftLyI2oJHja")
                .build();
    }
}