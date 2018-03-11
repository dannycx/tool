## 显示意图激活与隐式意图激活

You can use the [editor on GitHub](https://github.com/dcxing111/dcxing111.github.io/edit/master/index.md) to maintain and preview the content for your website in Markdown files.

Whenever you commit to this repository, GitHub Pages will run [Jekyll](https://jekyllrb.com/) to rebuild the pages in your site, from the content in your Markdown files.

###   Intent意图
作用：激活组件

```markdown
-显示意图激活：
-方式一：
-Intent intent = new Intent(context,Target.class);
-startXxx(intent);
-方式二：
-Intent intent = new Intent();
-intent.setClass(context,Target.class);
-startXxx(intent);
--方式三：
-Intent intent = new Intent();
-intent.setClassName(context,"com.xxx.Target");
-startXxx(intent);
-方式四：
-Intent intent = new Intent();
-intent.setComponent(new ComponentName(context,Target.class));
-startXxx(intent);


隐式意图激活：
# Header 1
## Header 2
### Header 3

- Bulleted
- List

1. Numbered
2. List

**Bold** and _Italic_ and `Code` text

[Link](url) and ![Image](src)
```

For more details see [GitHub Flavored Markdown](https://guides.github.com/features/mastering-markdown/).

### Jekyll Themes

Your Pages site will use the layout and styles from the Jekyll theme you have selected in your [repository settings](https://github.com/dcxing111/dcxing111.github.io/settings). The name of this theme is saved in the Jekyll `_config.yml` configuration file.

### Support or Contact

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.
