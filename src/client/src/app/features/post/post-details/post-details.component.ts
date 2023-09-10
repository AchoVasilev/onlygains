import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { PostDetailsResource } from 'app/shared/shared-module/models/post';
import { Observable } from 'rxjs';

@Component({
  selector: 'gains-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class PostDetailsComponent implements OnInit {
  post$?: Observable<PostDetailsResource>;
  postId: string;

  constructor(private postService: PostService, private route: ActivatedRoute, private sanitizer: DomSanitizer) {
    this.postId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    this.post$ = this.postService.getById(this.route.snapshot.params['id']);

    //this.post = this.getPost();
  }

  // getTag(): TagViewResource {
  //   return {
  //     name: this.post?.category.name || 'categoryyy',
  //     id: this.post?.category.id || '',
  //     translatedName: this.post?.category.translatedName || '',
  //   };
  // }

  sanitize(text: string) {
    return this.sanitizer.bypassSecurityTrustHtml(text);
  }

  getPost(): PostDetailsResource {
    return {
      id: 'asdasdasdasd',
      imageUrls: [
        'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691941043/onlygains/categories/TheNotebook_pd92nm.jpg',
        'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691941224/onlygains/categories/brutally-hardcore-gyms-you-need-to-train-at-before-you-die-652x400-10-1496399800_ahp7xa.jpg',
        'https://res.cloudinary.com/dpo3vbxnl/image/upload/v1691942376/onlygains/categories/hiking-trail-names_fgpox2.jpg',
      ],
      text: ` Sadipscing labore amet rebum est et justo gubergren. Et eirmod
      ipsum sit diam ut magna lorem. Nonumy vero labore lorem sanctus
      rebum et lorem magna kasd, stet amet magna accusam consetetur
      eirmod. Kasd accusam sit ipsum sadipscing et at at sanctus et.
      Ipsum sit gubergren dolores et, consetetur justo invidunt at et
      aliquyam ut et vero clita. Diam sea sea no sed dolores diam
      nonumy, gubergren sit stet no diam kasd vero.`,
      title: 'asdasdasdasdasdasd',
      createdBy: {
        id: 'asdasdasd',
        fullName: 'Gosho goshev',
        username: 'gosho@abv.bg',
      },
      createdAt: '2023-08-29 16:34:49.104848+00',
      category: {
        id: 'asdasdasd',
        imageUrl: 'img',
        name: 'exercises',
        translatedName: 'exercises',
      },
      tags: [
        { id: 'asdasd', name: 'first', translatedName: 'tagtag' },
        { id: 'asdasd', name: 'first', translatedName: 'tagtag' },
        { id: 'asdasd', name: 'first', translatedName: 'tagtag' },
        { id: 'asdasd', name: 'first', translatedName: 'tagtag' },
        { id: 'asdasd', name: 'first', translatedName: 'tagtag' },
      ],
      comments: [
        {
          id: 'asdasd1',
          createdAt: '2023-08-29 16:34:49.104848+00',
          createdBy: {
            id: 'asdasd',
            fullName: 'gosho gosho',
            username: 'gosho@abv.bg',
          },
          text: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',
          replies: [],
          parentId: null
        },
        {
          id: 'asdasd2',
          createdAt: '2023-08-29 16:34:49.104848+00',
          createdBy: {
            id: 'asdasd',
            fullName: 'gosho gosho',
            username: 'gosho@abv.bg',
          },
          text: 'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb',
          parentId: null,
          replies: [
            {
              id: 'asdasdasd3',
              createdAt: '2023-08-29 16:34:49.104848+00',
              createdBy: {
                id: 'asdasd',
                fullName: 'gosho gosho',
                username: 'gosho@abv.bg',
              },
              text: 'cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc',
              replies: [],
              parentId: 'asdasd2'
            },
            {
              id: 'asdasdasd4',
              createdAt: '2023-08-29 16:34:49.104848+00',
              createdBy: {
                id: 'asdasd',
                fullName: 'gosho gosho',
                username: 'gosho@abv.bg',
              },
              text: 'dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd',
              parentId: 'asdasd2',
              replies: [
                {
                  id: 'asdasdasd5',
                  createdAt: '2023-08-29 16:34:49.104848+00',
                  createdBy: {
                    id: 'asdasd',
                    fullName: 'gosho gosho',
                    username: 'gosho@abv.bg',
                  },
                  text: 'vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv',
                  replies: [],
                  parentId: 'asdasdasd4'
                },
                {
                  id: 'asdasdasd6',
                  createdAt: '2023-08-29 16:34:49.104848+00',
                  createdBy: {
                    id: 'asdasd',
                    fullName: 'gosho gosho',
                    username: 'gosho@abv.bg',
                  },
                  text: 'gggggggggggggggggggggggggggggggggggggggggggggggggggggggg',
                  replies: [],
                  parentId: 'asdasdasd4'
                },
                {
                  id: 'asdasdasd7',
                  createdAt: '2023-08-29 16:34:49.104848+00',
                  createdBy: {
                    id: 'asdasd',
                    fullName: 'gosho gosho',
                    username: 'gosho@abv.bg',
                  },
                  text: 'eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',
                  parentId: 'asdasdasd4',
                  replies: [
                    {
                      id: 'asdasdasd8',
                      createdAt: '2023-08-29 16:34:49.104848+00',
                      createdBy: {
                        id: 'asdasd',
                        fullName: 'gosho gosho',
                        username: 'gosho@abv.bg',
                      },
                      text: 'asdasdasafsafsafasfasfarqwrqwrqwrqwrqwr',
                      replies: [],
                      parentId: 'asdasdasd7'
                    },
                    {
                      id: 'asdasdasd9',
                      createdAt: '2023-08-29 16:34:49.104848+00',
                      createdBy: {
                        id: 'asdasd',
                        fullName: 'gosho gosho',
                        username: 'gosho@abv.bg',
                      },
                      text: 'tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt',
                      replies: [],
                      parentId: 'asdasdasd7'
                    },
                    {
                      id: 'asdasdasd10',
                      createdAt: '2023-08-29 16:34:49.104848+00',
                      createdBy: {
                        id: 'asdasd',
                        fullName: 'gosho gosho',
                        username: 'gosho@abv.bg',
                      },
                      text: 'hhjhkkhkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk',
                      replies: [],
                      parentId: 'asdasdasd7'
                    },
                  ],
                },
              ],
            },
            {
              id: 'asdasdasd11',
              createdAt: '2023-08-29 16:34:49.104848+00',
              createdBy: {
                id: 'asdasd',
                fullName: 'gosho gosho',
                username: 'gosho@abv.bg',
              },
              text: 'mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm',
              replies: [],
              parentId: null
            },
          ],
        },
      ],
    };
  }
}
