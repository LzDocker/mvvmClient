
package android.databinding;
import com.docker.moduleplayer.BR;
@javax.annotation.Generated("Android Data Binding")
class DataBinderMapper  {
    final static int TARGET_MIN_SDK = 15;
    public DataBinderMapper() {
    }
    public android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View view, int layoutId) {
        switch(layoutId) {
                case com.docker.moduleplayer.R.layout.moduleplayer_activity_player_home:
                    return com.docker.moduleplayer.databinding.ModuleplayerActivityPlayerHomeBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_fragment_knowledge:
                    return com.docker.moduleplayer.databinding.ModuleplayerFragmentKnowledgeBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_activity_knowledge_detial:
                    return com.docker.moduleplayer.databinding.ModuleplayerActivityKnowledgeDetialBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_pro_item:
                    return com.docker.moduleplayer.databinding.ModuleplayerProItemBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_fragment_nav:
                    return com.docker.moduleplayer.databinding.ModuleplayerFragmentNavBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_fragment_index:
                    return com.docker.moduleplayer.databinding.ModuleplayerFragmentIndexBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_konwledge_item:
                    return com.docker.moduleplayer.databinding.ModuleplayerKonwledgeItemBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_fragment_subcode:
                    return com.docker.moduleplayer.databinding.ModuleplayerFragmentSubcodeBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_common_item:
                    return com.docker.moduleplayer.databinding.ModuleplayerCommonItemBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_activity_player_main:
                    return com.docker.moduleplayer.databinding.ModuleplayerActivityPlayerMainBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_item_nav:
                    return com.docker.moduleplayer.databinding.ModuleplayerItemNavBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_fragment_pro:
                    return com.docker.moduleplayer.databinding.ModuleplayerFragmentProBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_item_article_index:
                    return com.docker.moduleplayer.databinding.ModuleplayerItemArticleIndexBinding.bind(view, bindingComponent);
                case com.docker.moduleplayer.R.layout.moduleplayer_fragment_common:
                    return com.docker.moduleplayer.databinding.ModuleplayerFragmentCommonBinding.bind(view, bindingComponent);
        }
        return null;
    }
    android.databinding.ViewDataBinding getDataBinder(android.databinding.DataBindingComponent bindingComponent, android.view.View[] views, int layoutId) {
        switch(layoutId) {
        }
        return null;
    }
    int getLayoutId(String tag) {
        if (tag == null) {
            return 0;
        }
        final int code = tag.hashCode();
        switch(code) {
            case 1442723467: {
                if(tag.equals("layout/moduleplayer_activity_player_home_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_activity_player_home;
                }
                break;
            }
            case -343554995: {
                if(tag.equals("layout/moduleplayer_fragment_knowledge_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_fragment_knowledge;
                }
                break;
            }
            case -2092556554: {
                if(tag.equals("layout/moduleplayer_activity_knowledge_detial_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_activity_knowledge_detial;
                }
                break;
            }
            case -88434909: {
                if(tag.equals("layout/moduleplayer_pro_item_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_pro_item;
                }
                break;
            }
            case 104463922: {
                if(tag.equals("layout/moduleplayer_fragment_nav_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_fragment_nav;
                }
                break;
            }
            case 1818836641: {
                if(tag.equals("layout/moduleplayer_fragment_index_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_fragment_index;
                }
                break;
            }
            case 1673271060: {
                if(tag.equals("layout/moduleplayer_konwledge_item_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_konwledge_item;
                }
                break;
            }
            case 725010364: {
                if(tag.equals("layout/moduleplayer_fragment_subcode_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_fragment_subcode;
                }
                break;
            }
            case 1570039915: {
                if(tag.equals("layout/moduleplayer_common_item_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_common_item;
                }
                break;
            }
            case 1572829413: {
                if(tag.equals("layout/moduleplayer_activity_player_main_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_activity_player_main;
                }
                break;
            }
            case 967190325: {
                if(tag.equals("layout/moduleplayer_item_nav_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_item_nav;
                }
                break;
            }
            case 106810684: {
                if(tag.equals("layout/moduleplayer_fragment_pro_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_fragment_pro;
                }
                break;
            }
            case -1540759653: {
                if(tag.equals("layout/moduleplayer_item_article_index_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_item_article_index;
                }
                break;
            }
            case -165264386: {
                if(tag.equals("layout/moduleplayer_fragment_common_0")) {
                    return com.docker.moduleplayer.R.layout.moduleplayer_fragment_common;
                }
                break;
            }
        }
        return 0;
    }
    String convertBrIdToString(int id) {
        if (id < 0 || id >= InnerBrLookup.sKeys.length) {
            return null;
        }
        return InnerBrLookup.sKeys[id];
    }
    private static class InnerBrLookup {
        static String[] sKeys = new String[]{
            "_all"
            ,"item"};
    }
}