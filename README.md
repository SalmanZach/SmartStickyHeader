# SmartStickyHeader

![ic_launcher](https://cloud.githubusercontent.com/assets/11782272/26771640/607f75fe-49dd-11e7-88c1-77389b7b1f46.png)


A Custom Header View With Multiple Items to make selection from categories.


![smartstickyheader](https://cloud.githubusercontent.com/assets/11782272/26771770/d4419b06-49de-11e7-9733-442280175c7c.gif)

#### Android API 15+
#### Stick With ScrollView 
#### Stick With RecyclerView

### Example.

Make A SmartStickyAnimator By Using Default one  

     final SmartBaseHeaderAnimator animator = new StickyAnimatorDefault() {

            @Override
            public AnimatorBuilder getAnimatorBuilder() {
            
            set Views on header for animate.            
            
                final View imageOne, imageTwo, imageThree, imageFour, imageFive, logo, foreground;
                imageOne = getHeader().findViewById(R.id.image_one);
                imageTwo = getHeader().findViewById(R.id.image_two);
                imageThree = getHeader().findViewById(R.id.image_three);
                imageFour = getHeader().findViewById(R.id.image_four);
                imageFive = getHeader().findViewById(R.id.image_five);
                logo = getHeader().findViewById(R.id.logo);
                foreground = getHeader().findViewById(R.id.foreground);

             setUp final positions.

                int space = (headerImage.getWidth()) / 5;
                return AnimatorBuilder.create()
                        .applyTranslation(imageOne, new Point((space - imageOne.getWidth()) / 2, 0))
                        .applyTranslation(imageTwo, new Point(space + ((space - imageOne.getWidth()) / 2), 0))
                        .applyTranslation(imageThree, new Point((2 * space) + ((space - imageOne.getWidth()) / 2), 0))
                        .applyTranslation(imageFour, new Point((3 * space) + ((space - imageOne.getWidth()) / 2), 0))
                        .applyTranslation(imageFive, new Point((4 * space) + ((space - imageOne.getWidth()) / 2), 0))
                        .applyTranslation(logo, new Point(-headerLayout.getWidth() / 2, 0))
                        .applyFade(logo, 0)
                        .applyFade(foreground, 1);
            }
        };
