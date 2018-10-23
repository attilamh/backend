package com.webapp.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;

@Service
public class EmailService {
	@Autowired
	private JavaMailSender emailSender;
/*
	 /** Authorizes the installed application to access user's protected data. *
	 private static Credential authorize() throws Exception {
	   // load client secrets
	   GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
	       new InputStreamReader(EmailService.class.getResourceAsStream("/client_secrets.json")));
	   // set up authorization code flow
	   GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	       httpTransport, JSON_FACTORY, clientSecrets,
	       Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(dataStoreFactory)
	      .build();
	   // authorize
	   return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
	}
	private static HttpTransport httpTransport;
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	GoogleClientSecrets clientSecrets;
	GoogleAuthorizationCodeFlow flow;
	private static com.google.api.services.gmail.Gmail client;

*/	 

	Credential credential;

	@Value("${gmail.client.clientId}")
	private String clientId;

	@Value("${gmail.client.clientSecret}")
	private String clientSecret;

	@Value("${gmail.client.redirectUri}")
	private String redirectUri;	
	
	
	public void sendSimpleMessage(String to, String subject, String text) {
		SimpleMailMessage message = new SimpleMailMessage(); 
		message.setTo(to); 
		message.setSubject(subject); 
		message.setText(text);
		emailSender.send(message);
	}
	/*
	private String authorize() throws Exception {
		AuthorizationCodeRequestUrl authorizationUrl;
		if (flow == null) {
			Details web = new Details();
			web.setClientId(clientId);
			web.setClientSecret(clientSecret);
			clientSecrets = new GoogleClientSecrets().setWeb(web);
			httpTransport = GoogleNetHttpTransport.newTrustedTransport();
			flow = new GoogleAuthorizationCodeFlow.Builder(httpTransport, JSON_FACTORY, clientSecrets,
					Collections.singleton(GmailScopes.GMAIL_READONLY)).build();
		}
		authorizationUrl = flow.newAuthorizationUrl().setRedirectUri(redirectUri);

		System.out.println("gamil authorizationUrl ->" + authorizationUrl);
		return authorizationUrl.build();
	}
	*/
}
