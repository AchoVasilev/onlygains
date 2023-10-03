export interface EditorOnInit {
    imageUrls: string[];
    title?: string | null;
}

export interface EditorInputChange {
    body: string;
    previewText?: string | null;
}