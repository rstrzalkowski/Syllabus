export interface SubjectPage {
  content: Subject[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Subject {
  id: string;
  createdAt: Date;
  updatedAt: Date;
  imageUrl: string
  name: string;
  activeRealisationsCount: number
  abbreviation: string;
  archived: boolean;
}
