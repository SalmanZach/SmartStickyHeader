# SmartStickyHeader

![ic_launcher](https://cloud.githubusercontent.com/assets/11782272/26771640/607f75fe-49dd-11e7-88c1-77389b7b1f46.png)


A Custom Header View With Multiple Items to make selection from categories.


![smartstickyheader](https://cloud.githubusercontent.com/assets/11782272/26771770/d4419b06-49de-11e7-9733-442280175c7c.gif)

#### Android API 15+
#### Stick With ScrollView 
#### Stick With RecyclerView

### Example.

Make A SmartStickyAnimator By Using Default one.

     final SmartBaseHeaderAnimator animator = new StickyAnimatorDefault() {

            @Override
            public AnimatorBuilder getAnimatorBuilder() {
            
            //set Views on header for animate.            
            
                final View imageOne, imageTwo, imageThree, imageFour, imageFive, logo, foreground;
                imageOne = getHeader().findViewById(R.id.image_one);
                imageTwo = getHeader().findViewById(R.id.image_two);
                imageThree = getHeader().findViewById(R.id.image_three);
                imageFour = getHeader().findViewById(R.id.image_four);
                imageFive = getHeader().findViewById(R.id.image_five);
                logo = getHeader().findViewById(R.id.logo);
                foreground = getHeader().findViewById(R.id.foreground);

             //setUp final positions.

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



then make a Builder and stick to a scroller(ScollView, RecyclerView), add Header View and finally animator.
                           
                SmartStickyBuilder
                // scorller can be a ScrollView or RecyclerView                
                .stickTo(scroller)
                .setHeader(headerLayout)
                //here you can add touch on header 
                .setHeaderTouchListener
                .minHeightHeader(getResources().getDimensionPixelSize(R.dimen.header_height))
                .animator(animator)
                .build();
                
                
you can add TouchListener on HeaderView

     public interface StickyHeaderTouchListener {
      void onHeaderScrolled();
       void onHeaderClicked();
      }
      
      
License
-------

    Copyright 2017 Salman Zach

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
