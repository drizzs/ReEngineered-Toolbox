package xyz.brassgoggledcoders.reengineeredtoolbox.test.item;

import com.builtbroken.mc.testing.junit.AbstractTest;
import com.builtbroken.mc.testing.junit.VoltzTestRunner;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import xyz.brassgoggledcoders.reengineeredtoolbox.api.face.capability.single.CapabilityFaceHolder;
import xyz.brassgoggledcoders.reengineeredtoolbox.api.face.capability.single.IFaceHolder;
import xyz.brassgoggledcoders.reengineeredtoolbox.item.ItemFace;
import xyz.brassgoggledcoders.reengineeredtoolbox.test.utils.Setup;

import static xyz.brassgoggledcoders.reengineeredtoolbox.ReEngineeredToolbox.MOD_ID;

@RunWith(VoltzTestRunner.class)
public class TestItemFace extends AbstractTest {

    @Override
    @BeforeClass
    public void setUpForEntireClass() {
        Setup.setupRegistries();
        Setup.setupCaps();
    }

    @Test
    public void testCapabilityInit() {
        NBTTagCompound tagCompound = new NBTTagCompound();
        tagCompound.setString("face", new ResourceLocation(MOD_ID, "blank").toString());
        ItemStack itemStack = new ItemStack(new ItemFace(), 1, 0, tagCompound);

        IFaceHolder faceHolder = itemStack.getCapability(CapabilityFaceHolder.FACE_HOLDER, null);
        assert faceHolder != null;
        assert new ResourceLocation(MOD_ID, "blank").equals(faceHolder.getFace().getRegistryName());
    }
}
