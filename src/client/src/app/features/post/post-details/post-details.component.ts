import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PostService } from 'app/core/services/post/post.service';
import { PostDetailsResource } from 'app/shared/shared-module/models/post';
import { TagViewResource } from 'app/shared/shared-module/models/tag';

@Component({
  selector: 'gains-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.scss'],
})
export class PostDetailsComponent implements OnInit {
  post?: PostDetailsResource;
  postId: string;

  constructor(private postService: PostService, private route: ActivatedRoute) {
    this.postId = this.route.snapshot.params['postId'];
  }

  ngOnInit(): void {
    //this.postService.getById(this.postId).subscribe(post => this.post = post);
  }

  getTag(): TagViewResource {
    return {
      name: this.post?.category.name || 'categoryyy',
      id: this.post?.category.id || '',
      translatedName: this.post?.category.translatedName || '',
    };
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
          id: 'asdasd',
          createdAt: '2023-08-29 16:34:49.104848+00',
          createdBy: {
            id: 'asdasd',
            fullName: 'gosho gosho',
            username: 'gosho@abv.bg',
          },
          text: 'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa',
          replies: [],
        },
        {
          id: 'asdasd',
          createdAt: '2023-08-29 16:34:49.104848+00',
          createdBy: {
            id: 'asdasd',
            fullName: 'gosho gosho',
            username: 'gosho@abv.bg',
          },
          text: 'bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb',
          replies: [
            {
              id: 'asdasdasd',
              createdAt: '2023-08-29 16:34:49.104848+00',
              createdBy: {
                id: 'asdasd',
                fullName: 'gosho gosho',
                username: 'gosho@abv.bg',
              },
              text: 'cccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc',
              replies: [],
            },
            {
              id: 'asdasdasd',
              createdAt: '2023-08-29 16:34:49.104848+00',
              createdBy: {
                id: 'asdasd',
                fullName: 'gosho gosho',
                username: 'gosho@abv.bg',
              },
              text: 'dddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd',
              replies: [
                {
                  id: 'asdasdasd',
                  createdAt: '2023-08-29 16:34:49.104848+00',
                  createdBy: {
                    id: 'asdasd',
                    fullName: 'gosho gosho',
                    username: 'gosho@abv.bg',
                  },
                  text: 'vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv',
                  replies: [],
                },
                {
                  id: 'asdasdasd',
                  createdAt: '2023-08-29 16:34:49.104848+00',
                  createdBy: {
                    id: 'asdasd',
                    fullName: 'gosho gosho',
                    username: 'gosho@abv.bg',
                  },
                  text: 'gggggggggggggggggggggggggggggggggggggggggggggggggggggggg',
                  replies: [],
                },
                {
                  id: 'asdasdasd',
                  createdAt: '2023-08-29 16:34:49.104848+00',
                  createdBy: {
                    id: 'asdasd',
                    fullName: 'gosho gosho',
                    username: 'gosho@abv.bg',
                  },
                  text: 'eeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee',
                  replies: [
                    {
                      id: 'asdasdasd',
                      createdAt: '2023-08-29 16:34:49.104848+00',
                      createdBy: {
                        id: 'asdasd',
                        fullName: 'gosho gosho',
                        username: 'gosho@abv.bg',
                      },
                      text: 'asdasdasafsafsafasfasfarqwrqwrqwrqwrqwr',
                      replies: [],
                    },
                    {
                      id: 'asdasdasd',
                      createdAt: '2023-08-29 16:34:49.104848+00',
                      createdBy: {
                        id: 'asdasd',
                        fullName: 'gosho gosho',
                        username: 'gosho@abv.bg',
                      },
                      text: 'tttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttttt',
                      replies: [],
                    },
                    {
                      id: 'asdasdasd',
                      createdAt: '2023-08-29 16:34:49.104848+00',
                      createdBy: {
                        id: 'asdasd',
                        fullName: 'gosho gosho',
                        username: 'gosho@abv.bg',
                      },
                      text: 'hhjhkkhkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk',
                      replies: [],
                    },
                  ],
                },
              ],
            },
            {
              id: 'asdasdasd',
              createdAt: '2023-08-29 16:34:49.104848+00',
              createdBy: {
                id: 'asdasd',
                fullName: 'gosho gosho',
                username: 'gosho@abv.bg',
              },
              text: 'mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm',
              replies: [],
            },
          ],
        },
      ],
    };
  }
}
