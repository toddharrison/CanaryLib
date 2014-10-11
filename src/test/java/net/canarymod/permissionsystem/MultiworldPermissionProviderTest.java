package net.canarymod.permissionsystem;

import org.junit.Assert;

public class MultiworldPermissionProviderTest {

    @org.junit.Test
    public void testQueryPermission() throws Exception {
        MultiworldPermissionProvider parent = new MultiworldPermissionProvider();
        MultiworldPermissionProvider provider = new MultiworldPermissionProvider(parent);


        provider.addPermission("canary.world.*", true, 0);
        provider.addPermission("canary.world.mod", true, 1);
        provider.addPermission("canary.world.mod.dig", false, 2);
        provider.addPermission("canary.world.mod.fly", true, 2);
        provider.addPermission("canary.commands.*", false, 3);
        provider.addPermission("canary.commands.tp", true, 4);
        parent.addPermission("canary.super.administrator", true, 5);

        Assert.assertNotNull("Provider should have a parent, parent was null", provider.getParent());
        Assert.assertFalse("canary.world.mod.dig must be false as it is denied!", provider.queryPermission("canary.world.mod.dig"));
        Assert.assertTrue("canary.world.mod.fly must be true as it is granted!", provider.queryPermission("canary.world.mod.fly"));
        Assert.assertTrue("canary.world.look must be true (implicit) as world.* is granted!", provider.queryPermission("canary.world.look"));

        Assert.assertTrue("Command TP should be granted, was not!", provider.queryPermission("canary.commands.tp"));
        Assert.assertFalse("Command help should be denied, was not!", provider.queryPermission("canary.commands.help"));

        Assert.assertTrue("Administrator check shold be granted, was not!", parent.queryPermission("canary.super.administrator"));
        Assert.assertTrue("Administrator check shold be granted, was not!", provider.queryPermission("canary.super.administrator"));
    }
}