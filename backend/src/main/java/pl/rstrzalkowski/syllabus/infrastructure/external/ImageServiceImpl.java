package pl.rstrzalkowski.syllabus.infrastructure.external;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import pl.rstrzalkowski.syllabus.domain.shared.ImageService;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private final RestTemplate restTemplate = new RestTemplate();


    public String saveImage(MultipartFile image) throws IOException {
        LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();
        String url = "https://thumbsnap.com/api/upload";

        map.add("media", new MultipartInputStreamFileResource(image.getInputStream(), UUID.randomUUID().toString()));
        map.add("key", "000030fb27ef30f3b511142e75f18b71");

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(map, headers);

        String body = restTemplate.postForEntity(url, entity, String.class).getBody();
        JsonObject json = new Gson().fromJson(body, JsonObject.class);

        String imageId = json.getAsJsonObject("data").get("id").getAsString();

        return "https://thumbsnap.com/s/" + imageId + ".jpg";
    }

}
