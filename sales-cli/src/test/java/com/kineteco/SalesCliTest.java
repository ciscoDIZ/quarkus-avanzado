package com.kineteco;

import io.quarkus.test.junit.main.Launch;
import io.quarkus.test.junit.main.LaunchResult;
import io.quarkus.test.junit.main.QuarkusMainTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusMainTest
public class SalesCliTest {

   @Test
   @Launch("view")
   public void testLaunchDefaultView(LaunchResult result) {
      assertTrue(result.getOutput().contains("email='penatibus.et@lectusa.com"));
   }

   @Test
   @Launch({"view", "--id=2"})
   public void testLaunchIdArgument(LaunchResult result) {
      assertTrue(result.getOutput().contains("email='nibh@ultricesposuere.edu"));
   }
}
