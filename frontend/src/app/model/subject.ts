export interface SubjectPage {
  content: Subject[],
  totalPages: number,
  last: boolean,
  number: number
}

export interface Subject {
  id: number;
  createdAt: Date;
  updatedAt: Date;
  name: string;
  activeRealisationsCount: number
  abbreviation: string;
  archived: boolean;
}
