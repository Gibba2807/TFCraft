package TFC;

import java.util.Map;

import TFC.ASM.Transform.TF_EntityFallingSand;
import TFC.ASM.Transform.TF_RenderBlock;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

public class TFCASMLoadingPlugin implements IFMLLoadingPlugin
{
	public static boolean runtimeDeobf;

	@Override
	public String[] getLibraryRequestClass() {
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[]{TF_RenderBlock.class.getName(), TF_EntityFallingSand.class.getName()};
	}

	@Override
	public String getModContainerClass() {
		return TerraFirmaCraft.class.getName();
	}

	@Override
	public String getSetupClass() {
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		// TODO Auto-generated method stub
		runtimeDeobf = (Boolean) data.get("runtimeDeobfuscationEnabled");
	}

}