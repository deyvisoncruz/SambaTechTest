/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import static com.amazonaws.services.s3.model.CryptoStorageMode.ObjectMetadata;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Deyvison
 */
public class CloudAmazonS3 {
    
    public AmazonS3 s3;
    public BasicAWSCredentials credenciais;

    public BasicAWSCredentials getCredenciais() {
        return credenciais;
    }

    public void setCredenciais(BasicAWSCredentials credenciais) {
        this.credenciais = credenciais;
    }
    public CloudAmazonS3()
    {
        try {
            
       
            BasicAWSCredentials awsCreds = new BasicAWSCredentials("access_key_id", "secret_key_id");
            s3 = AmazonS3ClientBuilder.standard()
                        .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                        .build();
         } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Erro na integraçao com o Amazon S3!", ""));
        
        }
        
    }
    public void submitFile(InputStream s, ObjectMetadata m) throws IOException
    {
        try {
            
         
        // s3client.putObject(new PutObjectRequest(
          //  		                 bucketName, keyName, file));
        s3.putObject(new PutObjectRequest("reposambatechdeyvison", s.toString(), s,m));
           
         FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Gravação no S3 da Amazon efetuada com sucesso, este usuário já está apto a enviar videos!", ""));
        
        

         } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " +
            		"means your request made it " +
                    "to Amazon S3, but was rejected with an error response" +
                    " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " +
            		"means the client encountered " +
                    "an internal error while trying to " +
                    "communicate with S3, " +
                    "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }

    }
    
    public S3ObjectInputStream downFile( String nomeArquivo) {
        String bucketName = "";

        ObjectListing objectListing = s3.listObjects(new ListObjectsRequest().withBucketName("reposambatechdeyvison"));
        for (S3ObjectSummary objeto : objectListing.getObjectSummaries()) {
                if (objeto.getKey() == nomeArquivo) {
                        bucketName = objeto.getBucketName();
                        break;
                }
        }		

        S3Object object = s3.getObject(new GetObjectRequest(bucketName, nomeArquivo));
        S3ObjectInputStream streamArquivo = object.getObjectContent();

        return streamArquivo;
    }
    public AmazonS3 getS3() {
        return s3;
    }

    public void setS3(AmazonS3 s3) {
        this.s3 = s3;
    }

	

}
