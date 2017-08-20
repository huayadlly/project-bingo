package cn.taike.paper.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huayadlly on 2017/8/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RemoteAppControllerTest {

    @Autowired
    private RemoteAppController remoteAppController;

    private String text = "A year ago, I move to another city with my parents, so I had to say goodbye to my friends and the most beautiful place. I have a lot of fun there, I always went to pick up the fruits in the garden and caught the fish in the clear wate. I will never forget about everything. When I have time, I will go back.A year ago, I move to another city with my parents, so I had to say goodbye to my friends and the most beautiful place. I have a lot of fun there, I always went to pick up the fruits in the garden and caught the fish in the clear wate. I will never forget about everything. When I have time, I will go back.A year ago, I move to another city with my parents, so I had to say goodbye to my friends and the most beautiful place. I have a lot of fun there, I always went to pick up the fruits in the garden and caught the fish in the clear wate. I will never forget about everything. When I have time, I will go back.A year ago, I move to another city with my parents, so I had to say goodbye to my friends and the most beautiful place. I have a lot of fun there, I always went to pick up the fruits in the garden and caught the fish in the clear wate. I will never forget about everything. When I have time, I will go back.A year ago, I move to another city with my parents, so I had to say goodbye to my friends and the most beautiful place. I have a lot of fun there, I always went to pick up the fruits in the garden and caught the fish in the clear wate. I will never forget about everything. When I have time, I will go back.A year ago, I move to another city with my parents, so I had to say goodbye to my friends and the most beautiful place. I have a lot of fun there, I always went to pick up the fruits in the garden and caught the fish in the clear wate. I will never forget about everything. When I have time, I will go back.A year ago, I move to another city with my parents, so I had to say goodbye to my friends and the most beautiful place. I have a lot of fun there, I always went to pick up the fruits in the garden and caught the fish in the clear wate. I will never forget about everything. When I have time, I will go back.A year ago, I move to another city with my parents, so I had to say goodbye to my friends and the most beautiful place. I have a lot of fun there, I always went to pick up the fruits in the garden and caught the fish in the clear wate. I will never forget about everything. When I have time, I will go back.";

    @Test
    public void submitImageTest() {

        RemoteAppController.ImageRequest image = new RemoteAppController.ImageRequest();
        image.setImgUrl("/abc/text.png");
        remoteAppController.submitImage("123", image);
    }

}
