export interface PostConfig {
  base_url: string;
  suffix: string;
  selector?: string;
  height?: string;
  menubar?: boolean;
  language?: string;
  language_url?: string;
  plugins?: string[];
  toolbar?: string;
  promotion: boolean;
  branding: boolean;
  content_style?: string;
  images_upload_handler?: any
}

export const defaultPostConfig: PostConfig = {
  base_url: '/tinymce',
  suffix: '.min',
  selector: '#textarea',
  height: 'calc(100vh - 88px)',
  menubar: true,
  language: 'bg_BG',
  language_url: 'assets/langs/bg_BG.js',
  toolbar:
    'undo redo | formatselect | bold italic backcolor | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help | preview',
  promotion: false,
  branding: false,
  plugins: ['anchor', 'link', 'lists', 'image', 'preview'],
};
